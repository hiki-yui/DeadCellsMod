package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

public class KillingDeck extends DeadCellsCard{

    public static final String BASE_ID = "deadCells:KillingDeck";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public KillingDeck(){
        super(BASE_ID,STRINGS.NAME,"img/card/KillingDeck.png",
                1,STRINGS.DESCRIPTION, CardType.ATTACK,
                AbstractDeadCellsEnum.DEAD_CELLS, CardRarity.COMMON,
                CardTarget.ENEMY);

        this.damage = this.baseDamage = 3;
        this.setBackgroundTexture(DeadCellsModInitializer.PURPLE2_GREEN2_SKILL_CARD, DeadCellsModInitializer.PURPLE2_GREEN2_SKILL_CARD_PORTRAIT);

    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage),
                AbstractGameAction.AttackEffect.BLUNT_LIGHT,true));
        AbstractCard c = abstractPlayer.discardPile.getRandomCard(CardType.SKILL,true);
        if (c != null)
            addToBot(new DiscardToHandAction(c));
        if (upgraded){
            c = abstractPlayer.drawPile.getRandomCard(CardType.ATTACK,true);
            if (c !=null )
                abstractPlayer.drawPile.moveToHand(c);
        }


    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeDamage(1);
            this.rawDescription = STRINGS.UPGRADE_DESCRIPTION;
            this.upgraded = true;
            this.upgradeName();
            this.initializeDescription();
        }


    }
}
