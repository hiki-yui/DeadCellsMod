package deadCellsMod.cn.infinite.stsmod.Relics;

import basemod.abstracts.CustomCard;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.AddAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.common.TransformCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import deadCellsMod.cn.infinite.stsmod.action.AddCardToHandAction;
import deadCellsMod.cn.infinite.stsmod.cards.FerrymanSLantern;
import deadCellsMod.cn.infinite.stsmod.cards.Roll;

//绿宝石护符
public class EmeraldAmulet extends DeadCellsRelic {
    public static final String ID = "deadCells:EmeraldAmulet";
    private static final RelicStrings strings = CardCrawlGame.languagePack.getRelicStrings(ID);

    public EmeraldAmulet(){
        super(ID,new Texture("img/relics/EmeraldAmulet.png"),RelicTier.STARTER,LandingSound.MAGICAL);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new EmeraldAmulet();
    }


    @Override
    public void atBattleStart() {
        CustomCard roll = new Roll();
        roll.upgrade();
        roll.exhaust = true;
        roll.rawDescription+=" NL 消耗 。 NL 保留 。";
        roll.selfRetain =true;
        roll.initializeDescription();

        addToBot(new AddCardToHandAction(roll));
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player,this));
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        super.onEnterRoom(room);
        FerrymanSLantern.AmNumber = 0;
    }

    @Override
    public void onVictory() {
        super.onVictory();
        FerrymanSLantern.AmNumber = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return strings.DESCRIPTIONS[0]+1+strings.DESCRIPTIONS[1];
    }

    @Override
    public void update() {
        super.update();
    }
}
