package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.enums.DeadCellsTags;

public class Punishment extends DeadCellsCard{

    public static final String BASE_ID = "deadCells:Punishment";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/Punishment.png";

    public Punishment(){
        super(BASE_ID,STRINGS.NAME,IMG,2,STRINGS.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractDeadCellsEnum.DEAD_CELLS, AbstractCard.CardRarity.UNCOMMON, CardTarget.SELF);
        this.setBackgroundTexture(DeadCellsModInitializer.GREEN_SKILL_CARD, DeadCellsModInitializer.GREEN_SKILL_CARD_PORTRAIT);

        this.baseBlock = 10;
        this.magicNumber = this.baseMagicNumber = 1;
        this.tags.add(DeadCellsTags.SHIELD);
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
        addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,this.block));
        for (int i = 0;i<this.magicNumber;i++){
            AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.ATTACK).makeCopy();
            card.exhaust = true;
            addToBot(new NewQueueCardAction(card,true,true,true));
        }
    }
}
