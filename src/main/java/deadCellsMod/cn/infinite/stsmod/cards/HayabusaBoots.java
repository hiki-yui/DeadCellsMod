package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;
import deadCellsMod.cn.infinite.stsmod.utils.CardUtils;

public class HayabusaBoots extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:HayabusaBoots";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public HayabusaBoots(){
        super(BASE_ID,STRINGS.NAME,"img/card/HayabusaBoots.png",1,STRINGS.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCardEnum.DEAD_CELLS, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);

        this.baseDamage = 4;
        this.magicNumber = this.baseMagicNumber = 3;
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
        int i = 1;
        for ( ;i<this.magicNumber;i++){
            addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.BLUNT_LIGHT,true));
        }
        addToBot(new DamageAllEnemiesAction(abstractPlayer, CardUtils.forDamageAllEnemies(this.damage), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY,true));
    }
}
