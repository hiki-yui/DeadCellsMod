package deadCellsMod.cn.infinite.stsmod.event;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import deadCellsMod.cn.infinite.stsmod.Relics.RamRune;

public class DarkSoulEvent extends AbstractImageEvent {
    public static final String ID = "deadCells:DarkSoulEvent";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS=eventStrings.DESCRIPTIONS;
    private CurScreen screen;
    public static final String[] OPTIONS=eventStrings.OPTIONS;
    private boolean wall= false, fire= false, man = false, changeTitle = false;

    public DarkSoulEvent(){
        super(NAME, DESCRIPTIONS[0], "img/event/darksoulevent0.png");
        this.imageEventText.setDialogOption(OPTIONS[0]);
        this.screen = CurScreen.SEE;
    }

    private enum CurScreen {
        SEE,
        BREAK_DOWN,
        GO_AHEAD,
        SEE_FWD,
        LEAVE_SEE,
        LEAVE;

        private CurScreen() {
        }
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.screen) {
            case SEE:
                if(buttonPressed == 0){
                    this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                    this.imageEventText.clearAllDialogs();
                    if(AbstractDungeon.player.hasRelic("deadCells:RamRune")){
                        this.imageEventText.setDialogOption(OPTIONS[3], new RamRune());
                        this.imageEventText.setDialogOption(OPTIONS[4]);
                        this.screen = CurScreen.BREAK_DOWN;
                    }else{
                        this.imageEventText.setDialogOption(OPTIONS[1], true);
                        this.imageEventText.setDialogOption(OPTIONS[2]);
                        this.screen = CurScreen.LEAVE_SEE;
                    }
                }
                break;
            case LEAVE_SEE:
                if(buttonPressed == 1) {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[8]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(OPTIONS[10]);
                    this.screen = CurScreen.LEAVE;
                }
                break;
            case BREAK_DOWN:
                if(buttonPressed == 0){
                    changeTitle = true;
                    this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(OPTIONS[5]);
                    this.screen = CurScreen.GO_AHEAD;
                } else if (buttonPressed == 1) {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[8]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(OPTIONS[10]);
                    this.screen = CurScreen.LEAVE;
                }
                break;
            case GO_AHEAD:
                if(buttonPressed == 0){
                    this.imageEventText.loadImage("img/event/darksoulevent1.png");
                    this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(OPTIONS[6],wall);
                    this.imageEventText.setDialogOption(OPTIONS[7],fire);
                    this.imageEventText.setDialogOption(OPTIONS[8],man);
                    this.imageEventText.setDialogOption(OPTIONS[10]);
                    this.screen = CurScreen.SEE_FWD;
                }
                break;
            case SEE_FWD:
                if(buttonPressed == 0){
                    this.imageEventText.updateBodyText(DESCRIPTIONS[5]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(OPTIONS[9]);
                    this.wall = true;
                    this.screen = CurScreen.GO_AHEAD;
                } else if (buttonPressed == 1) {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[6]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(OPTIONS[9]);
                    this.fire = true;

                    //heal to full health
                    int amt = AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth;
                    AbstractDungeon.player.heal(amt);
                    AbstractEvent.logMetricHealAtCost(ID, "Healed", 0, amt);

                    //add gold or not
                    RamRune.RANDOM = AbstractDungeon.eventRng.random(100);

                    this.screen = CurScreen.GO_AHEAD;
                } else if (buttonPressed == 2) {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[7]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(OPTIONS[9]);
                    this.man = true;

                    //add gold and card
                    int gold = AbstractDungeon.eventRng.random(1, 30);
                    AbstractDungeon.getCurrRoom().rewards.clear();
                    AbstractDungeon.getCurrRoom().addGoldToRewards(gold);
                    AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                    AbstractDungeon.combatRewardScreen.open();

                    this.screen = CurScreen.GO_AHEAD;
                } else if (buttonPressed == 3) {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[8]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(OPTIONS[10]);
                    this.screen = CurScreen.LEAVE;
                }
                break;
            case LEAVE:
                this.imageEventText.updateDialogOption(0, OPTIONS[10]);
                this.imageEventText.clearRemainingOptions();
                this.openMap();
                break;
        }
        //It is best to look at examples of what to do here in the basegame event classes, but essentially when you click on a dialog option the index of the pressed button is passed here as buttonPressed.
    }

    @Override
    public void update() {
        super.update();
        if(changeTitle) {
            this.title = DESCRIPTIONS[2];
            this.imageEventText.show(this.title, this.body);
            this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
            this.changeTitle = false;
        }
    }
}
