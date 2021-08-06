package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import deadCellsMod.cn.infinite.stsmod.cards.ThrowingKnife;
import deadCellsMod.cn.infinite.stsmod.utils.ImgUtils;

import java.util.ArrayList;

public class AttackDefendPower extends AbstractPower {

    public static final String BASE_ID = "deadCells:AttackDefendPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);
    private boolean canGive = true;

    public AttackDefendPower(AbstractCreature owner,int amount){
        this.ID = BASE_ID;
        this.name = STRINGS.NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        ImgUtils.setPowerImg(this);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0]+amount+STRINGS.DESCRIPTIONS[1];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        //该方法调用前会把card加入card的使用历史中，即当前卡的位置为一下cards的size-1,上一张卡的位置为cards.size-2
        ArrayList<AbstractCard> cards = AbstractDungeon.actionManager.cardsPlayedThisTurn;
        /*AbstractCard theCardBeforeLastCard =*/
        //只有卡牌使用历史中至少有2张牌时才能触发,放置越界异常
        if (cards.size() >=2) {
            AbstractCard lastCard =  cards.get(cards.size() - 2);
            if (canGive) {//交替运行,当技能和攻击交互使用一组时才会给卡,交替完后需要重新计算一组,这一组需要一张卡来间隔
                if (card.type == AbstractCard.CardType.SKILL) {
                    if (lastCard.type == AbstractCard.CardType.ATTACK) {
                        addToBot(new MakeTempCardInHandAction(new ThrowingKnife(),this.amount));
                        canGive = false;
                    }
                } else if (card.type == AbstractCard.CardType.ATTACK) {
                    if (lastCard.type == AbstractCard.CardType.SKILL) {
                        addToBot(new MakeTempCardInHandAction(new ThrowingKnife(),this.amount));
                        canGive = false;
                    }
                }
            } else {
                //这个步骤可以用于组间隔,在一组触发后才会走到这个分支,防止当前卡牌和上一组的卡牌进行交互,使下一张卡走上面的分支
                canGive = true;
            }
        }
        super.onUseCard(card, action);
    }

    @Override
    public void atStartOfTurn() {
        //回合修正
        this.canGive = true;
        super.atStartOfTurn();
    }
}
