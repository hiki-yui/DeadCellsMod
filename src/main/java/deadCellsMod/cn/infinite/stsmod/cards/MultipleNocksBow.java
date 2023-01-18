package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

public class MultipleNocksBow extends DeadCellsCard{

    public static final String BASE_ID = "deadCells:MultipleNocksBow";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public MultipleNocksBow(){
        super(BASE_ID,STRINGS.NAME,"img/card/MultipleNocksBow.png",1,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.COMMON,CardTarget.ALL_ENEMY);

        this.setBackgroundTexture(DeadCellsModInitializer.PURPLE_ATTACK_CARD, DeadCellsModInitializer.PURPLE_ATTACK_CARD_PORTRAIT);

        this.damage = this.baseDamage = 6;
        this.magicNumber = this.baseMagicNumber = 2;
        this.changeNum = this.baseChangeNum = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeDamage(2);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (int i = 0;i<this.magicNumber;i++)
            addToBot(new DamageRandomEnemyAction(new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new DiscardAction(abstractPlayer,abstractPlayer,this.changeNum,true));
    }
}
