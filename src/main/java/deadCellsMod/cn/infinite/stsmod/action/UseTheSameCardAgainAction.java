package deadCellsMod.cn.infinite.stsmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class UseTheSameCardAgainAction extends AbstractGameAction {
    private AbstractCard card;

    public UseTheSameCardAgainAction(AbstractCard card,AbstractCreature source, AbstractCreature target){
        this.source = source;
        this.target = target;
        this.card = card;
    }

    @Override
    public void update() {
        AbstractCard willUseCard = card.makeStatEquivalentCopy();
        AbstractDungeon.player.limbo.addToBottom(willUseCard);
        willUseCard.current_x = card.current_x;
        willUseCard.current_y = card.current_y;
        willUseCard.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
        willUseCard.target_y = Settings.HEIGHT / 2.0F;
        willUseCard.purgeOnUse = true;
        willUseCard.freeToPlayOnce = true;
        AbstractDungeon.actionManager.addCardQueueItem(
                new CardQueueItem(willUseCard,(AbstractMonster) ((this.target != null) ? this.target : AbstractDungeon.getRandomMonster()),
                        card.energyOnUse, true, true), false);
        isDone = true;
    }
}
