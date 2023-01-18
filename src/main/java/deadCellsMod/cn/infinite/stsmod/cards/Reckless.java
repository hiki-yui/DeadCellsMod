package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.utils.CardUtils;

import java.util.ArrayList;

public class Reckless extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:Reckless";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public Reckless(){
        super(BASE_ID,STRINGS.NAME,"img/card/Reckless.png",1,STRINGS.DESCRIPTION,CardType.SKILL, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.SELF);
        this.setBackgroundTexture(DeadCellsModInitializer.GOLD_SKILL_CARD, DeadCellsModInitializer.GOLD_SKILL_CARD_PORTRAIT);

        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeMagicNumber(1);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cards = p.drawPile.group;
        ArrayList<AbstractCard> attack = new ArrayList<>();

        for (AbstractCard card : cards) {
            if (card.type == CardType.ATTACK) {
                attack.add(card);
            }
        }
        if(cards.size()>0) {
            if (attack.size() <= this.magicNumber) {
                for (AbstractCard card : attack) {
                    addToBot(new AbstractGameAction() {
                        @Override
                        public void update() {
                            card.exhaust = true;
                            addToBot(new NewQueueCardAction(card, AbstractDungeon.getRandomMonster(), true, true));
                            p.drawPile.removeCard(card);
                            isDone = true;
                        }
                    });
                }
            } else {
                for (int i = 0; i < this.magicNumber; i++) {
                    addToBot(new AbstractGameAction() {
                        @Override
                        public void update() {
                            int rnd = CardUtils.getRandomItemFromList(attack);
                            attack.get(rnd).exhaust = true;
                            addToBot(new NewQueueCardAction(attack.get(rnd), AbstractDungeon.getRandomMonster(), true, true));
                            p.drawPile.removeCard(attack.get(rnd));
                            attack.remove(rnd);
                            isDone = true;
                        }
                    });
                }
            }
        }
    }
}
