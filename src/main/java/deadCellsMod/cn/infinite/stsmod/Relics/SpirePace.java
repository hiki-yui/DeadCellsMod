package deadCellsMod.cn.infinite.stsmod.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class SpirePace extends CustomRelic {
    public static final String BASE_ID = "deadCells:SpirePace";
    private static final String IMG = "img/relics/spirePace.png";
    private static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(BASE_ID);

    public SpirePace(){
        super(BASE_ID,new Texture(IMG),RelicTier.BOSS,LandingSound.MAGICAL);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        ArrayList<AbstractCard> cardHistory = AbstractDungeon.actionManager.cardsPlayedThisTurn;
        if (cardHistory.size()>=1){
            //获取上一张卡
            //遗物先结算,卡组使用历史在遗物结算完后增加(卡被打出后只有被所有遗物结算完后才会被加入cardHistory,即真正被使用)
            AbstractCard lastCard = cardHistory.get(cardHistory.size()-1);
            if (lastCard.type != c.type){
                addToBot(new RelicAboveCreatureAction(AbstractDungeon.player,this));
                addToBot(new DrawCardAction(1));
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return STRINGS.DESCRIPTIONS[0]+1+STRINGS.DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SpirePace();
    }
}
