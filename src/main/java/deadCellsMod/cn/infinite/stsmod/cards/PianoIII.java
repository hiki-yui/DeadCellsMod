package deadCellsMod.cn.infinite.stsmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;

public class PianoIII extends PianoII {
    public static final String ID = "deadCells:PianoIII";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = strings.NAME;
    private static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "img/card/Piano.png";
    private static final int BASE_COST = 2;
    private static final int BASE_DAMAGE = 32;
    public PianoIII(){
       this(true);
    }

    public PianoIII(boolean preview){
        super(ID,NAME,IMG,BASE_COST,DESCRIPTION,CardType.ATTACK, CardColor.COLORLESS,CardRarity.SPECIAL,CardTarget.ALL_ENEMY);
        this.setBackgroundTexture(DeadCellsModInitializer.GREEN_ATTACK_CARD, DeadCellsModInitializer.GREEN_ATTACK_CARD_PORTRAIT);

        this.damage = this.baseDamage = BASE_DAMAGE;
        this.magicNumber = this.baseMagicNumber = this.baseDamage*2;
        this.exhaust = true;
        this.damageType = DamageInfo.DamageType.NORMAL;
        if (preview)
            this.cardsToPreview = new PianoIII(false);
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
        addToBot(new ApplyPowerAction(paramAbstractPlayer,paramAbstractPlayer,new RhythmPower(paramAbstractPlayer,this.makeCopy(),this)));
        addToBot(new MakeTempCardInHandAction(new PianoIII()));
    }


    /*@Override
    public AbstractCard makeCopy(){
        AbstractCard card = new PianoIII();
        if (upgraded){
            card.upgrade();
        }
        return card;
    }*/
    public class RhythmPower extends Piano.RhythmPower{
        public RhythmPower(AbstractCreature owner, AbstractCard forCard,AbstractCard maker){
            super(owner,forCard,maker,"节奏III","deadCells:RhythmPowerIII");
        }

        @Override
        public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
            return super.atDamageGive(damage, type, card);
        }

        @Override
        public void updateDescription() {
            this.description = "节奏IIII";
        }

    }
}
