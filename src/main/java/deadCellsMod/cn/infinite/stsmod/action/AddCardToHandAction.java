package deadCellsMod.cn.infinite.stsmod.action;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;

public class AddCardToHandAction extends AbstractGameAction {
    private AbstractCard card;

    public AddCardToHandAction(AbstractCard card){
        this.card =card;
    }

    @Override
    public void update() {
        //属性startDuration指的是动画的总持续时间
        //Duration指的是动画以播放的时间,动画的结束按钮只有isDone,且需要手动调用来让动画结束
        if (card!=null){
            if (AbstractDungeon.player.hand.group.size()<10){
                AbstractDungeon.player.hand.addToHand(card);
            }else {
                card.moveToDiscardPile();
                AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(card));
            }
        }
        this.isDone=true;
    }
}
