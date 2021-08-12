package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;


public class CorrosiveCloudPower extends DeadCellsPower {

    public static final String BASE_ID = "deadCells:CorrosiveCloudPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);

    public CorrosiveCloudPower(AbstractCreature owner,int amount){
        super(BASE_ID,STRINGS,owner,amount,PowerType.BUFF,false);
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0] + this.amount +STRINGS.DESCRIPTIONS[1];
    }
}
