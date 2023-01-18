package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;

public class PianoII extends Piano {
    public static final String ID = "deadCells:PianoII";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = strings.NAME;
    private static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "img/card/Piano.png";
    private static final int BASE_COST = 2;
    private static final int BASE_DAMAGE = 16;

    public PianoII(){
        super(ID,NAME,IMG,BASE_COST,DESCRIPTION,CardType.ATTACK,CardColor.COLORLESS,CardRarity.SPECIAL,CardTarget.ENEMY);
        this.setBackgroundTexture(DeadCellsModInitializer.GREEN_ATTACK_CARD, DeadCellsModInitializer.GREEN_ATTACK_CARD_PORTRAIT);

        this.damage = this.baseDamage = BASE_DAMAGE;
        this.magicNumber = this.baseMagicNumber = this.baseDamage*2;
        this.damageType= DamageInfo.DamageType.NORMAL;
        this.exhaust = true;
        this.cardsToPreview = new PianoIII();
        this.damageType = DamageInfo.DamageType.NORMAL;
    }
    PianoII(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target){
        super(id,name,img,cost,rawDescription,type,color,rarity,target);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(this.cost-1);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer paramAbstractPlayer, AbstractMonster paramAbstractMonster) {
        Piano card = new PianoIII();
        AbstractDungeon.actionManager.addToBottom(new SFXAction("deadCells:PIANO_II"));
        pianoFallowAction(paramAbstractPlayer,paramAbstractMonster,card);
        addToBot(new ApplyPowerAction(paramAbstractPlayer,paramAbstractPlayer,new RhythmPower(paramAbstractPlayer,card,this)));



        //卡的use方法被调用前就会将这张卡的计入使用历史
        /*AbstractCard lastCard = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(
                AbstractDungeon.actionManager.cardsPlayedThisCombat.size()-2);*/
       /* if (lastCard.getClass() == Piano.class){
            addToTop(new ApplyPowerAction(paramAbstractPlayer,paramAbstractPlayer,new RhythmPower(paramAbstractPlayer,false)));
        }*/

    }

   /* @Override
    public AbstractCard makeCopy(){
        AbstractCard card = new PianoII();
        if (upgraded){
            card.upgrade();
        }
        return card;
    }*/

    public class RhythmPower extends Piano.RhythmPower{
        public RhythmPower(AbstractCreature owner, AbstractCard forCard,AbstractCard maker){
            super(owner,forCard,maker,"节奏II","deadCells:RhythmPowerII");
        }

        @Override
        public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
            return super.atDamageGive(damage, type, card);
        }

        @Override
        public void updateDescription() {
            this.description = "节奏III";
        }
    }


}
