package deadCellsMod.cn.infinite.stsmod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;


public class BeginnerBow extends DeadCellsCard{

    public static final String BASE_ID = "deadCells:BeginnerBow";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public BeginnerBow(){
        super(BASE_ID,STRINGS.NAME,"img/card/BeginnerBow.png",1,STRINGS.DESCRIPTION,CardType.ATTACK,
                AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.BASIC,CardTarget.ENEMY);
        this.baseDamage = 9;
        this.magicNumber = this.baseMagicNumber = 1;
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
        addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new SelectCardsInHandAction(this.magicNumber,STRINGS.EXTENDED_DESCRIPTION[0],true,true,
                (card)-> (!card.retain&&!card.selfRetain),
                (list)-> {
                    for (AbstractCard card : list) {
                        card.retain = true;
                    }
                }));

    }
}
