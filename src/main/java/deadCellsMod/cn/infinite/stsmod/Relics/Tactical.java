package deadCellsMod.cn.infinite.stsmod.Relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;

//战术
public class Tactical extends PowerOfScroll {

    public static final String BASE_ID = "deadCells:Tactical";
    private static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(BASE_ID);
    private static final String IMG = "img/relics/tactical.png";
    private boolean isScreenUpOpened = false;

    public Tactical(){
        super(BASE_ID,new Texture(IMG),RelicTier.SPECIAL,LandingSound.MAGICAL);
        this.scrollType = ScrollType.PURPLE;
    }

    @Override
    public void update() {
        this.superUpdate();
        try {
            this.allUpdate(this.getClass().getMethod("updatePurple"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEquip() {
        isGame = true;
        selected = this.openPurpleSelectScreen(PURPLE_DELETE_CARD_NUM);
    }

    @Override
    public String getUpdatedDescription() {
        return STRINGS.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Tactical();
    }

}
