package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.utils.CardUtils;
import deadCellsMod.cn.infinite.stsmod.utils.ImgUtils;

public class RootGrenade extends GrenadeCard {

    public static final String BASE_ID = "deadCells:RootGrenade";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/RootGrenade.png";

    public RootGrenade(){
        super(BASE_ID,STRINGS.NAME,IMG,2,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.ALL_ENEMY);

        this.magicNumber = this.baseMagicNumber = 2;
        this.baseDamage = 5;
        this.baseBlock = 10;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeMagicNumber(1);
            this.upgradeBlock(2);
            this.upgradeDamage(1);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAllEnemiesAction(abstractPlayer, CardUtils.forDamageAllEnemies(this.damage), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,this.block));
        for (AbstractMonster monster: AbstractDungeon.getMonsters().monsters){
            addToBot(new ApplyPowerAction(monster,abstractPlayer,new ConstrictedPower(monster,abstractPlayer,this.magicNumber)));
        }
    }

    static class ConstrictedPower extends com.megacrit.cardcrawl.powers.ConstrictedPower{
        public static final String BASE_ID = "deadCells:ConstrictedPower";
        public static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);
        public ConstrictedPower(AbstractCreature target, AbstractCreature source, int fadeAmt){
            super(target,source,fadeAmt);
            this.name = STRINGS.NAME;
            this.ID = BASE_ID;
            ImgUtils.setPowerImg(this);
        }

        @Override
        public void updateDescription() {
            this.description = STRINGS.DESCRIPTIONS[0] + amount +STRINGS.DESCRIPTIONS[1];
        }

        @Override
        public void atStartOfTurn() {
            this.flashWithoutSound();
            this.playApplyPowerSfx();
            this.addToBot(new DamageAction(this.owner, new DamageInfo(this.source, this.amount, DamageInfo.DamageType.THORNS)));
        }

        @Override
        public void atEndOfTurn(boolean isPlayer) {
            /*super.atEndOfTurn(isPlayer);*/
        }
    }
}
