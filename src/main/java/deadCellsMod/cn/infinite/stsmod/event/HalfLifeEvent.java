package deadCellsMod.cn.infinite.stsmod.event;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.cards.CrowBar;

import java.util.ArrayList;

public class HalfLifeEvent extends AbstractImageEvent {
    public static final String ID = "deadCells:HalfLifeEvent";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS=eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS=eventStrings.OPTIONS;
    private CurScreen screen;

    public HalfLifeEvent(){
        super(NAME, DESCRIPTIONS[0], "img/event/halflifeevent.png");
        this.imageEventText.setDialogOption(OPTIONS[0]);
        this.imageEventText.setDialogOption(OPTIONS[1]);
        this.screen = CurScreen.NOTICE;
    }

    private enum CurScreen{
        NOTICE,
        SEE,
        LEAVE;
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.screen){
            case NOTICE:
                if(buttonPressed == 0){
                    this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(OPTIONS[2]);
                    this.imageEventText.setDialogOption(OPTIONS[3]);
                    this.imageEventText.setDialogOption(OPTIONS[4]);
                    this.screen = CurScreen.SEE;
                }else{
                    this.imageEventText.updateBodyText(DESCRIPTIONS[5]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(OPTIONS[5]);
                    this.screen = CurScreen.LEAVE;
                }

                break;
            case SEE:
                if(buttonPressed == 0){
                    this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(OPTIONS[5]);
                    //get Crowbar
                    this.replaceAttacks();
                    this.screen = CurScreen.LEAVE;
                } else if (buttonPressed == 1) {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(OPTIONS[5]);
                    //get grenade
                    this.getGrenade();
                    this.screen = CurScreen.LEAVE;
                } else if (buttonPressed == 2) {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(OPTIONS[5]);
                    AbstractDungeon.player.loseGold(Math.min(AbstractDungeon.player.gold, 50));
                    this.replaceAttacks();
                    this.getGrenade();
                    this.screen = CurScreen.LEAVE;
                }
                break;
            case LEAVE:
                this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                this.imageEventText.clearRemainingOptions();
                this.openMap();
                break;
        }
    }

    private void replaceAttacks() {
        ArrayList<AbstractCard> masterDeck = AbstractDungeon.player.masterDeck.group;

        int i, atk = 0;
        for(i = masterDeck.size() - 1; i >= 0; --i) {
            AbstractCard card = (AbstractCard)masterDeck.get(i);
            if (card.tags.contains(AbstractCard.CardTags.STARTER_STRIKE)) {
                AbstractDungeon.player.masterDeck.removeCard(card);
                atk++;
            }
        }

        for(i = 0; i < atk; ++i) {
            AbstractCard c = new CrowBar();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
        }

    }

    private void getGrenade(){
        int cardRNG = AbstractDungeon.eventRng.random(DeadCellsModInitializer.GRENADE_POOL.size()-1);
        AbstractCard c = DeadCellsModInitializer.GRENADE_POOL.get(cardRNG);
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
    }
}
