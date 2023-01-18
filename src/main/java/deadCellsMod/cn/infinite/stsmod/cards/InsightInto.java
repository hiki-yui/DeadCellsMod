package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.powers.ReflectionSpeedUpPower;

import java.util.Map;

//洞察
public class InsightInto extends DeadCellsCard{

    public static final String BASE_ID = "deadCells:InsightInto";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/InsightInto.png";
    private static final ReflectionSpeedUpPower RSP = new ReflectionSpeedUpPower(AbstractDungeon.player,1);

    public InsightInto(){
        super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.SKILL, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.SELF);
        this.setBackgroundTexture(DeadCellsModInitializer.GOLD_SKILL_CARD, DeadCellsModInitializer.GOLD_SKILL_CARD_PORTRAIT);

    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeBaseCost(0);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Map<String,Integer> map = RSP.think();
        RSP.giveCard(map);
    }


}
