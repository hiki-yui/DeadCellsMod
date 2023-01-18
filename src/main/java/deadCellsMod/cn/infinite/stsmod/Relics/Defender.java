package deadCellsMod.cn.infinite.stsmod.Relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Defender extends DeadCellsRelic{
    public static final String BASE_ID = "deadCells:Defender";
    private static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(BASE_ID);
    private static final String IMG = "img/relics/Defender.png";

    public Defender() {
        super(BASE_ID,new Texture(IMG) , RelicTier.COMMON, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return STRINGS.DESCRIPTIONS[0] ;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Defender();
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        if (counter < 2)
            flash();
        counter++;
    }

    @Override
    public void onPlayerEndTurn() {
        super.onPlayerEndTurn();
        if (counter == 2){
            grayscale = true;
        }
    }

    @Override
    public void onLoseHp(int damageAmount) {

    }

    @Override
    public int onLoseHpLast(int damageAmount) {
        if (counter == 1) {
            System.out.println(0.8 * damageAmount);
            System.out.println((int) (0.8 * damageAmount));
            damageAmount = (int) (0.8 * damageAmount);
        }
        if (counter == 2 && damageAmount>0)
            damageAmount -= 1;
        return super.onLoseHpLast(damageAmount);

    }

    @Override
    public void onVictory() {
        counter = 0;
        grayscale = false;
        super.onVictory();
    }
}
