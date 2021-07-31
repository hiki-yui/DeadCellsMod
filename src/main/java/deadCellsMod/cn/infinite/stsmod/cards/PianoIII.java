package deadCellsMod.cn.infinite.stsmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PianoIII extends PianoII {
    public static final String ID = "deadCells:PianoIII";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = strings.NAME;
    private static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "img/card/Piano.png";
    private static final int BASE_COST = 3;
    private static final int BASE_DAMAGE = 32;
    public PianoIII(){
        super(ID,NAME,IMG,BASE_COST,DESCRIPTION,CardType.ATTACK, CardColor.COLORLESS,CardRarity.SPECIAL,CardTarget.ALL_ENEMY);

        this.damage = this.baseDamage = BASE_DAMAGE;
        this.magicNumber = this.baseMagicNumber = this.baseDamage*2;
        this.exhaust = true;
        this.damageType = DamageInfo.DamageType.NORMAL;

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(this.cost-1);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer paramAbstractPlayer, AbstractMonster paramAbstractMonster){
        AbstractDungeon.actionManager.addToBottom(new SFXAction("deadCells:PIANO_III"));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(paramAbstractPlayer,
                this.baseDamage,this.damageType, AbstractGameAction.AttackEffect.NONE));
    }


    /*@Override
    public AbstractCard makeCopy(){
        AbstractCard card = new PianoIII();
        if (upgraded){
            card.upgrade();
        }
        return card;
    }*/
}
