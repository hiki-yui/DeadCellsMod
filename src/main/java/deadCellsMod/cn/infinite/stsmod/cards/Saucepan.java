package deadCellsMod.cn.infinite.stsmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;

public class Saucepan extends CustomCard {

    public static final String ID = "deadCells:Saucepan";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = strings.NAME;
    private static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "img/card/Saucepan.png";

    public Saucepan(){
        super(ID,NAME,IMG,1,DESCRIPTION,CardType.ATTACK,
                AbstractCardEnum.DEAD_CELLS,CardRarity.BASIC,CardTarget.ENEMY);

        this.baseDamage = 8;
        this.baseMagicNumber = 1;
        this.damageType = DamageInfo.DamageType.NORMAL;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.rawDescription = strings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractMonster.Intent intent = abstractMonster.intent;
        AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractMonster,
                new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        if ( intent == AbstractMonster.Intent.ATTACK  || intent == AbstractMonster.Intent.ATTACK_BUFF || intent == AbstractMonster.Intent.ATTACK_DEBUFF || intent == AbstractMonster.Intent.ATTACK_DEFEND){
            if (this.upgraded){
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractMonster,abstractPlayer,
                        new VulnerablePower(abstractMonster,this.baseMagicNumber,false)));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractMonster,abstractPlayer,
                        new WeakPower(abstractMonster,this.baseMagicNumber,false)));
                addToTop(new WaitAction(0.5F));
            }

            AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractMonster,
                    new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
    }
   /* @Override
    public AbstractCard makeCopy(){
        AbstractCard card = new Saucepan();
        if (upgraded) {
            card.upgrade();
        }
        return card;
    }*/
}
