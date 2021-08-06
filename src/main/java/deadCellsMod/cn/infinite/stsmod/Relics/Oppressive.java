package deadCellsMod.cn.infinite.stsmod.Relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

//暴虐
public class Oppressive extends PowerOfScroll {

    public static final String BASE_ID = "deadCells:Oppressive";
    private static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(BASE_ID);
    private static final String IMG = "img/relics/oppressive.png";
    private static final int UPGRADE_CARD_NUM = 1;
    private static final int STRENGTH = 1;

    private boolean isScreenUpOpened = false;

    public Oppressive(){
        super(BASE_ID,new Texture(IMG),RelicTier.SPECIAL,LandingSound.MAGICAL);
        this.scrollType = ScrollType.RED;
    }

    @Override
    public String getUpdatedDescription() {
        return STRINGS.DESCRIPTIONS[0]+UPGRADE_CARD_NUM+STRINGS.DESCRIPTIONS[1]+STRENGTH+STRINGS.DESCRIPTIONS[2];
    }

    @Override
    public void update() {
        this.superUpdate();
        try {
            this.allUpdate(this.getClass().getMethod("updateRed"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEquip() {
        isGame = true;
        selected = this.openRedSelectScreen(RED_UPGRADED_CARD_NUM);
    }

    @Override
    public void atBattleStart() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player,null,new StrengthPower(AbstractDungeon.player,1),1));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Oppressive();
    }
}
