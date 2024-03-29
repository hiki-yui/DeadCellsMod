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
import deadCellsMod.cn.infinite.stsmod.enums.DeadCellsTags;
import deadCellsMod.cn.infinite.stsmod.powers.FrostbitePower;
import deadCellsMod.cn.infinite.stsmod.utils.CardUtils;

//冰冻手雷
public class IceGrenade extends GrenadeCard{

    public static final String BASE_ID = "deadCells:IceGrenade";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/IceGrenade.png";

    public IceGrenade(){
        super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.ALL_ENEMY);
        this.setBackgroundTexture(DeadCellsModInitializer.RED2_PURPLE2_ATTACK_CARD, DeadCellsModInitializer.RED2_PURPLE2_ATTACK_CARD_PORTRAIT);

        this.baseDamage = 3;
        this.magicNumber = this.baseMagicNumber = 3;
        /*this.exhaust = true;*/
        this.tags.add(DeadCellsTags.GRENADE);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeMagicNumber(1);
            this.upgradeDamage(2);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAllEnemiesAction(abstractPlayer, CardUtils.forDamageAllEnemies(this.damage), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters){
            addToBot(new ApplyPowerAction(monster,abstractPlayer,new FrostbitePower(monster,abstractPlayer,this.magicNumber),this.magicNumber));
        }
        super.use(abstractPlayer,abstractMonster);
    }

   /* @Override
    public void triggerOnManualDiscard() {
       addToBot(new UseTheSameCardAgainAction(this,AbstractDungeon.player,null));
    }
*/
}
