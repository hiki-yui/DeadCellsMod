package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.powers.OilPower;
import deadCellsMod.cn.infinite.stsmod.utils.CardUtils;

public class OilGrenade extends GrenadeCard {

    public static final String BASE_ID = "deadCells:OilGrenade";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/OilGrenade.png";

    public OilGrenade(){
        super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.COMMON,CardTarget.ALL_ENEMY);
        this.setBackgroundTexture(DeadCellsModInitializer.RED2_PURPLE2_ATTACK_CARD, DeadCellsModInitializer.RED2_PURPLE2_ATTACK_CARD_PORTRAIT);

        this.baseDamage = 8;
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeMagicNumber(1);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAllEnemiesAction(abstractPlayer, CardUtils.forDamageAllEnemies(this.damage), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters){
            addToBot(new ApplyPowerAction(monster,abstractPlayer,new OilPower(monster,this.magicNumber),this.magicNumber));
        }
        super.use(abstractPlayer,abstractMonster);
    }
}
