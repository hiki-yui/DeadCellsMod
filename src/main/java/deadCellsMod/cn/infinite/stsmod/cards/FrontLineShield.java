package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.action.AddCardToHandAction;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.enums.DeadCellsTags;

public class FrontLineShield extends DeadCellsCard {
    public static final String BASE_ID = "deadCells:FrontLineShield";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public FrontLineShield(){
        super(BASE_ID,STRINGS.NAME,"img/card/FrontLineShield.png",1,STRINGS.DESCRIPTION,CardType.SKILL, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.COMMON,CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = 2;
        this.baseBlock = 6;
        this.tags.add(DeadCellsTags.SHIELD);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeBlock(3);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,this.block));
        for (int i = 0;i<this.magicNumber;i++){
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.ATTACK);
                    card = card.makeCopy();
                    if (!card.exhaust){
                        if ("ZHS".equals(Settings.language.toString())){
                            card.rawDescription += " NL 消耗 .";
                        }else {
                            card.rawDescription += " NL Exhaust .";
                        }
                        card.exhaust = true;
                    }

                    if (!card.isEthereal){
                        if ("ZHS".equals(Settings.language.toString())){
                            card.rawDescription += " NL 虚无 .";
                        }else {
                            card.rawDescription += " NL Ethereal .";
                        }
                        card.isEthereal = true;
                    }

                    addToBot(new AddCardToHandAction(card));
                    isDone = true;
                }
            });
        }
    }
}
