package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.powers.MagneticPower;

public class MagneticGrenade extends GrenadeCard {

    public static final String BASE_ID = "deadCells:MagneticGrenade";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/MagneticGrenade.png";

    public MagneticGrenade(){
        super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.COMMON,CardTarget.ENEMY);
        this.setBackgroundTexture(DeadCellsModInitializer.PURPLE_ATTACK_CARD, DeadCellsModInitializer.PURPLE_ATTACK_CARD_PORTRAIT);

        this.baseDamage = 3;
        this.magicNumber = baseMagicNumber = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeDamage(1);
            this.upgradeMagicNumber(1);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (int i = 0; i < 2; i++) {
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage), AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
        }
        for (AbstractMonster monster: AbstractDungeon.getMonsters().monsters){
            addToBot(new ApplyPowerAction(monster,abstractPlayer,new MagneticPower(monster,abstractPlayer,this.magicNumber),this.magicNumber));
        }
        super.use(abstractPlayer,abstractMonster);
    }
}
