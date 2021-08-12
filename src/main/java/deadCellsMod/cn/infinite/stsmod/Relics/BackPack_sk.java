package deadCellsMod.cn.infinite.stsmod.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import deadCellsMod.cn.infinite.stsmod.cards.Roll;
import deadCellsMod.cn.infinite.stsmod.cards.Roll_ButNot;
import deadCellsMod.cn.infinite.stsmod.cards.Strike_king;

import java.util.ArrayList;


public class BackPack_sk extends CustomRelic {
    public static final String ID = "deadCells:BackPack_sk";
    private static final RelicStrings strings = CardCrawlGame.languagePack.getRelicStrings(ID);
    private static final String IMG = "img/relics/backpack_sk.png";

    public BackPack_sk() {
        //BOSS遗物
        super(ID, new Texture(IMG), /*RelicTier.BOSS*/RelicTier.BOSS, AbstractRelic.LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return strings.DESCRIPTIONS[0] + 3 + strings.DESCRIPTIONS[1];
    }

    @Override
    public void onEquip() {
        //需求,获取遗物时最大将卡组中的3张打击替换为 翻滚(roll),小于三张则尽可能的替换
        CardGroup deck = AbstractDungeon.player.masterDeck;
        ArrayList<AbstractCard> cardDeck = deck.group;
        //可以删除的卡
        ArrayList<AbstractCard> canRemoveCard = new ArrayList<>();
        int strikeAmount = 0;
        //如果起始打击卡已经有三张了打破循环
        if (cardDeck != null && !cardDeck.isEmpty()) {
            for (AbstractCard card : cardDeck) {
                if (strikeAmount == 3) {
                    break;
                }
                //判断是否有起始攻击卡
                if (card.hasTag(AbstractCard.CardTags.STARTER_STRIKE)) {
                    //可添加的翻滚数增加1
                    strikeAmount++;
                    //删除一张防御，循环检查和删除不能同时进行,因为list集合在遍历时不能被修改,而以下代码需要对list集合做修改
                    /*deck.removeCard(card);*/
                    canRemoveCard.add(card);
                }
            }
        }
        if (strikeAmount != 0) {
            //替换卡牌
            cardReplaceToRoll(deck, canRemoveCard, strikeAmount, strings);
        }
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        AbstractPlayer player = AbstractDungeon.player;
        if (Roll.class.equals(c.getClass())) {
            //随机获取一张技能牌
            AbstractCard card = player.drawPile.getRandomCard(AbstractCard.CardType.SKILL, true);
            if (card != null) {
                if (Roll.class.equals(card.getClass())) {
                    card = new Roll_ButNot();
                }
            }
            useExhaustCard(card, player);
        }
    }


    @Override
    public AbstractRelic makeCopy() {
        return new BackPack_sk();
    }

    public static void useExhaustCard(AbstractCard card, AbstractCreature player) {
        if (card != null) {
            AbstractCard theUseCard = card.makeSameInstanceOf();
            if (card.upgraded) {
                theUseCard.upgrade();
            }
            /*theUseCard.baseDamage = card.baseDamage;
            theUseCard.baseBlock = card.baseBlock;
            theUseCard.baseMagicNumber = card.baseMagicNumber;
            theUseCard.magicNumber = card.magicNumber;
            theUseCard.baseDiscard = card.baseDiscard;
            theUseCard.baseDraw = card.baseDraw;
            theUseCard.baseHeal = card.heal;*/
            theUseCard.freeToPlayOnce=true;
            theUseCard.exhaust = true;
            theUseCard.rawDescription += " NL 消耗 。";
            theUseCard.isSeen = true;
            theUseCard.initializeDescription();
            AbstractDungeon.actionManager.addToTop(new WaitAction(0.4F));
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(theUseCard, AbstractDungeon.getRandomMonster(),
                    theUseCard.energyOnUse, true, true));
            /*player.useCard(card,AbstractDungeon.getRandomMonster(),player.energy.energy);*/
            /*addToTop(new UseCardAction(card,AbstractDungeon.getRandomMonster()));*/
            /*card.moveToDiscardPile();*/
        } else {
            AbstractDungeon.effectList.add(new ThoughtBubble(player.dialogX, player.dialogY, 3.0F, "我的抽排堆 #r没有合适的牌", true));
        }
    }

    public static void cardReplaceToRoll(CardGroup deck, ArrayList<AbstractCard> canRemoveCard, int RollAmount, RelicStrings strings) {
        if (canRemoveCard.size() != 0) {
            //删除卡
            for (AbstractCard card : canRemoveCard) {
                deck.removeCard(card);
            }
            CardGroup cardGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            AbstractCard roll = new Roll();
            //在卡组里加入roll卡,最大添加3张
            for (int i = 0; i < RollAmount; i++) {
                //获取追踪效果,就是获取卡是卡牌塞到卡组中的尾巴效果(像彗星一样)?
                UnlockTracker.markCardAsSeen(Roll.ID);
                AbstractCard rollCopy = roll.makeCopy();
                rollCopy.isSeen = true;//设置卡可见?

                for (AbstractRelic r : AbstractDungeon.player.relics) {
                    //这个循环是搬过来的,用于兼容其他遗物对卡牌展示的修改效果
                    r.onPreviewObtainCard(rollCopy);
                }
                //将卡牌添加到cardGroup中
                cardGroup.addToBottom(rollCopy);
            }
            //将一个cardGroup展示出来
            AbstractDungeon.gridSelectScreen.openConfirmationGrid(cardGroup, strings.DESCRIPTIONS[2]);
        }
    }
}
