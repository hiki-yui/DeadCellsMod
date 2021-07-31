package deadCellsMod.cn.infinite.stsmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.DiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;

import javax.smartcardio.Card;

//翻滚
public class Roll extends CustomCard {

    public static final String ID = "deadCells:Roll";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = strings.NAME;
    private static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "img/card/roll.png";
    private static final int COST = 1;
    private static final int BASE_DRAW_CARD = 3;
    private static final int BASE_DEFEND = 8;
    private static final int UPGRADE_DEFEND = 3;

    public Roll(){
        this(ID,NAME,CardRarity.COMMON,AbstractCardEnum.DEAD_CELLS);
    }

    Roll(String id,String name,CardRarity rarity,CardColor cardColor){
        super(id,name,IMG,COST,DESCRIPTION,CardType.SKILL,
                cardColor,rarity,CardTarget.SELF);

        this.baseBlock = BASE_DEFEND;
        this.baseDraw = BASE_DRAW_CARD;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBlock(UPGRADE_DEFEND);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("deadCells:ROLL"));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.baseDraw,
                new AbstractGameAction() {
                    //这里照搬了刮削的源码
                    @Override
                    public void update() {
                        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.4F));//这行不太懂
                        tickDuration();//不太懂
                        //以下全解
                        if (this.isDone){
                            for (AbstractCard c : DrawCardAction.drawnCards) {
                                if (c.type==CardType.ATTACK) {
                                    AbstractDungeon.player.hand.moveToDiscardPile(c);
                                    c.triggerOnManualDiscard();
                                    GameActionManager.incrementDiscard(false);
                                }
                            }
                        }
                    }
                }));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(abstractPlayer,abstractPlayer,this.block));
    }

  /*  @Override
    public AbstractCard makeCopy(){
        AbstractCard card = new Roll();
        if (upgraded) {
            card.upgrade();
        }
        return card;
    }*/
}
