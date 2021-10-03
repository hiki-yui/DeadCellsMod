package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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
import com.megacrit.cardcrawl.powers.DoubleDamagePower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.utils.ImgUtils;

public class Piano extends DeadCellsCard {
    public static final String ID = "deadCells:Piano";
    private static final CardStrings strings;
    private static final String NAME;
    private static final String DESCRIPTION;
    private static final String IMG = "img/card/Piano.png";
    private static final int BASE_COST = 1;
    private static final int BASE_DAMAGE = 8;

    static {
        strings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = strings.NAME;
        DESCRIPTION = strings.DESCRIPTION;
    }

    public Piano(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id,name,img,cost,rawDescription,type,color,rarity,target);
    }

    public Piano() {
        super(ID, NAME, IMG, BASE_COST, DESCRIPTION, CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS, CardRarity.RARE, CardTarget.ENEMY);
        this.damage = this.baseDamage = BASE_DAMAGE;
        this.cardsToPreview = new PianoII();
        this.exhaust = true;
        this.damageType = DamageInfo.DamageType.NORMAL;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.exhaust = false;
            this.rawDescription = strings.UPGRADE_DESCRIPTION;
            this.upgradeName();
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer paramAbstractPlayer, AbstractMonster paramAbstractMonster) {
        Piano card = new PianoII();
        AbstractDungeon.actionManager.addToBottom(new SFXAction("deadCells:PIANO"));
        pianoFallowAction(paramAbstractPlayer,paramAbstractMonster,card);
        addToBot(new ApplyPowerAction(paramAbstractPlayer,paramAbstractPlayer,new RhythmPower(paramAbstractPlayer,card,this)));
    }
    /*@Override
    public AbstractCard makeCopy(){
        AbstractCard card = new Piano();
        if (upgraded) {
            card.upgrade();
        }
        return card;
    }*/

    void pianoFallowAction(AbstractCreature owner, AbstractCreature target, AbstractCard card){
        AbstractDungeon.actionManager.addToBottom(new DamageAction(target,new DamageInfo(owner,this.damage)));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card));
    }


    class RhythmPower extends DoubleDamagePower {
        AbstractCard card;
        boolean justApply;
        AbstractCard maker;

        RhythmPower(AbstractCreature owner, AbstractCard forCard,AbstractCard maker){
            this(owner,forCard, maker,"节奏","deadCells:RhythmPower");
        }

        RhythmPower(AbstractCreature owner, AbstractCard forCard,AbstractCard maker,String name,String id){
            super(owner,-1,false);
            this.ID = id;
            this.card = forCard;
            this.maker = maker;
            this.name = name;
            this.justApply = true;
            this.updateDescription();
            ImgUtils.setPowerImg(this);
        }


        @Override
        public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
            if (card.getClass() == this.card.getClass()){
                damage = super.atDamageGive(damage, type);
            }
            return damage;
        }

        @Override
        public void updateDescription() {
            this.description = "节奏II";
        }

        @Override
        public void onAfterUseCard(AbstractCard card, UseCardAction action) {
            if (card!=null) {
                if (!justApply) {
                    if (card.getClass() != this.card.getClass()) {
                        AbstractPlayer player = AbstractDungeon.player;
                        AbstractDungeon.effectList.add(new ThoughtBubble(player.dialogX, player.dialogY, 3.0F, "节奏 被 #r打断", true));
                    }
                    if (card.getClass() != this.maker.getClass()) {
                        addToTop(new RemoveSpecificPowerAction(owner, owner, this));
                    }
                } else {
                    justApply = false;
                }
            }
        }

    }
}
