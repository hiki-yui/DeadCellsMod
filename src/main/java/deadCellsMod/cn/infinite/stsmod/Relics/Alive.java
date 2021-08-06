package deadCellsMod.cn.infinite.stsmod.Relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Alive extends PowerOfScroll {

    public static final String BASE_ID = "deadCells:Alive";
    private static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(BASE_ID);
    private static final String IMG = "img/relics/alive.png";
    private static final int HEAL = 4;
    private static final int INCREASE_MAX_HP = 8;

    public Alive(){
        super(BASE_ID,new Texture(IMG),RelicTier.SPECIAL,LandingSound.MAGICAL);
        this.scrollType = ScrollType.GREEN;
    }

    @Override
    public void update() {
        this.superUpdate();
    }

    @Override
    public String getUpdatedDescription() {
        return STRINGS.DESCRIPTIONS[0]+INCREASE_MAX_HP+STRINGS.DESCRIPTIONS[1]+HEAL+STRINGS.DESCRIPTIONS[2];
    }

    @Override
    public void onEquip() {
        /*openGreenSelectScreen();*/
        AbstractDungeon.player.increaseMaxHp(INCREASE_MAX_HP,true);
    }

    @Override
    public void atPreBattle() {
        addToBot(new HealAction(AbstractDungeon.player,null,HEAL));
        super.atBattleStart();
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Alive();
    }
}
