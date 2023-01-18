package deadCellsMod.cn.infinite.stsmod.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import deadCellsMod.cn.infinite.stsmod.cards.Roll;

import java.util.ArrayList;


public class BackPack_at extends DeadCellsRelic {
    public static final String ID = "deadCells:BackPack_at";
    private static final RelicStrings strings = CardCrawlGame.languagePack.getRelicStrings(ID);
    private static final String IMG = "img/relics/backpack_at.png";

    public BackPack_at(){
        //BOSS遗物
        super(ID,new Texture(IMG),RelicTier.BOSS,LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return strings.DESCRIPTIONS[0]+1+strings.DESCRIPTIONS[1];
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster--;
        //需求,获取遗物时最大将卡组中的1张防御替换为 翻滚(roll),小于1张则尽可能的替换
        CardGroup deck = AbstractDungeon.player.masterDeck;
        ArrayList<AbstractCard> cardDeck = deck.group;
        //可以删除的卡
        ArrayList<AbstractCard> canRemoveCard = new ArrayList<>();
        int defendAmount = 0;
        //如果起始防御卡已经有1张了打破循环
        if (cardDeck != null && !cardDeck.isEmpty()) {
            for (AbstractCard card : cardDeck) {
                if (defendAmount == 1) {
                    break;
                }
                //判断是否有起始防御卡
                if (card.hasTag(AbstractCard.CardTags.STARTER_DEFEND)) {
                    //可添加的翻滚数增加1
                    defendAmount++;
                    //删除一张防御，循环检查和删除不能同时进行,因为list集合在遍历时不能被修改,而以下代码需要对list集合做修改
                    /*deck.removeCard(card);*/
                    canRemoveCard.add(card);
                }
            }
        }
        if (defendAmount!=0) {
            //替换卡
            BackPack_sk.cardReplaceToRoll(deck, canRemoveCard, defendAmount, strings);
        }
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        AbstractPlayer player = AbstractDungeon.player;
        if (c instanceof Roll){
            //随机获取一张攻击牌
            AbstractCard card = player.drawPile.getRandomCard(AbstractCard.CardType.ATTACK,true);
            BackPack_sk.useExhaustCard(card,player);
        }
    }

    @Override
    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster++;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BackPack_at();
    }
}
