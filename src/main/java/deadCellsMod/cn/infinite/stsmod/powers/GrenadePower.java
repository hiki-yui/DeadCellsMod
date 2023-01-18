package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import deadCellsMod.cn.infinite.stsmod.cards.GrenadeCard;

import java.util.LinkedList;
import java.util.List;

public class GrenadePower extends DeadCellsPower {
    public static final String BASE_ID = "deadCells:GrenadePower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack .getPowerStrings(BASE_ID);
    private static final List<GrenadeCard> gs = new LinkedList<>();

    public GrenadePower(AbstractCreature player, GrenadeCard g){
        super(BASE_ID,STRINGS,player,1,PowerType.BUFF,true);
        gs.add(g);
        this.updateDescription();
    }

    public void addG(GrenadeCard g){
        gs.add(g);
        this.flash();
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        StringBuilder s = new StringBuilder("");
        for (GrenadeCard g:gs) {
            s.append(g.originalName).append(STRINGS.DESCRIPTIONS[0]).append(g.counter).append(STRINGS.DESCRIPTIONS[1]).append('\n');
        }
        this.description = s.toString();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        super.atStartOfTurnPostDraw();
        gs.removeIf(c -> c.gTurnStart((AbstractPlayer) this.owner));
        if (gs.isEmpty())
            addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,this));
    }

    @Override
    public void onVictory() {
        gs.clear();
    }
}
