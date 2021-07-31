package deadCellsMod.cn.infinite.stsmod.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class Counterattack extends CustomRelic {

    public static final String BASE_ID = "deadCells:Counterattack";
    private static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(BASE_ID);
    private static int vigorAmount = 4;
    private static final String IMG = "img/relics/Counterattack.png";

    public Counterattack(){
        super(BASE_ID,new Texture(IMG),RelicTier.COMMON,LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return STRINGS.DESCRIPTIONS[0] + vigorAmount +STRINGS.DESCRIPTIONS[1];
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (AbstractDungeon.player.currentBlock >= damageAmount){
            addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new VigorPower(AbstractDungeon.player, vigorAmount),vigorAmount));
        }
        return super.onAttacked(info, damageAmount);
    }

}
