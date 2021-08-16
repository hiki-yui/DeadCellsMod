package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.action.HayabusaGauntletsAction;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

public class HayabusaGauntlets extends DeadCellsCard {
    public static final String BASE_ID = "deadCells:HayabusaGauntlets";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private boolean canMove = true;

    public HayabusaGauntlets(){
        super(BASE_ID,STRINGS.NAME,"img/card/HayabusaGauntlets.png",1,STRINGS.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);

        this.baseDamage = 4;
        this.heavyDamage = this.baseHeavyDamage = 7;
        this.magicNumber = this.baseMagicNumber = 2;
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
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (this.cost>0) {
            this.canMove = false;
        }
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new HayabusaGauntletsAction(abstractPlayer,abstractMonster,
                    this.damage,this.heavyDamage,((i + 1) == this.magicNumber),this.magicNumber));
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
    public void onMoveToDiscard() {
        if (!canMove){
            if (AbstractDungeon.player.hand.size()<10) {
                AbstractDungeon.player.discardPile.moveToHand(this);
            }
            canMove = true;
        }
    }

}
