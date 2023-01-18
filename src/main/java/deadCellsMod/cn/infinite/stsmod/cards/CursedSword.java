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

public class CursedSword extends DeadCellsCard {
    public static final String BASE_ID = "deadCells:CursedSword";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public CursedSword(){
        super(BASE_ID,STRINGS.NAME,"img/card/CursedSword.png",1,STRINGS.DESCRIPTION, CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS, CardRarity.RARE, CardTarget.ENEMY);
        this.setBackgroundTexture(DeadCellsModInitializer.RED_ATTACK_CARD, DeadCellsModInitializer.RED_ATTACK_CARD_PORTRAIT);
        this.baseDamage = 21;
        this.magicNumber = this.baseMagicNumber = 1 ;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeDamage(7);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.SLASH_HEAVY,true));
    }


}
