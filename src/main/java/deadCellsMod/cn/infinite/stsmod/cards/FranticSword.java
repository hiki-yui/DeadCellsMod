package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

public class FranticSword extends DeadCellsCard {
    public static final String BASE_ID = "deadCells:FranticSword";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public FranticSword(){
        super(BASE_ID,STRINGS.NAME,"img/card/FranticSword.png",1,STRINGS.DESCRIPTION, CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS, CardRarity.COMMON, CardTarget.ENEMY);
        this.setBackgroundTexture(DeadCellsModInitializer.RED_ATTACK_CARD, DeadCellsModInitializer.RED_ATTACK_CARD_PORTRAIT);
        this.baseDamage = 8;
        this.heavyDamage = this.baseHeavyDamage = 14;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeDamage(2);
            this.upgradeHeavyDamage(4);
            this.upgradeName();
        }
    }

    @Override
    public void applyPowers() {
        super.heavyDamageApplyPower();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardHeavyDamage(mo);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,
                abstractPlayer.currentHealth>(abstractPlayer.maxHealth/2)?this.damage:this.heavyDamage),
                AbstractGameAction.AttackEffect.SLASH_HEAVY,true));
    }
}
