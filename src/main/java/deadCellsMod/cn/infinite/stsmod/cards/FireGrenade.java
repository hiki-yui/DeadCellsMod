package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.action.GainBurnsPowerAction;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.utils.CardUtils;

public class FireGrenade extends GrenadeCard {

    public static final String BASE_ID = "deadCells:FireGrenade";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/FireGrenade.png";

    public FireGrenade(){
        super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.ALL_ENEMY);
        this.setBackgroundTexture(DeadCellsModInitializer.RED_ATTACK_CARD, DeadCellsModInitializer.RED_ATTACK_CARD_PORTRAIT);

        this.magicNumber = this.baseMagicNumber = 6;
        this.baseDamage = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeMagicNumber(3);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAllEnemiesAction(abstractPlayer, CardUtils.forDamageAllEnemies(this.damage), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            addToBot(new GainBurnsPowerAction(monster,abstractPlayer,this.magicNumber));
        }
        super.use(abstractPlayer,abstractMonster);

    }
}
