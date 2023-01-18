package deadCellsMod.cn.infinite.stsmod.Relics;

import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;

public class CorruptedArtifact extends DeadCellsRelic implements CustomSavable<Boolean> {

    public static final String BASE_ID = "deadCells:CorruptedArtifact";
    private static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(BASE_ID);
    private static final int gold = 500;
    private boolean curse = true;

    @Override
    public Boolean onSave() {
        return curse;
    }

    @Override
    public void onLoad(Boolean o) {
        if (o!=null) {
            this.curse = o;
            if (!o) {
                /*this.grayscale = true;*/
                this.usedUp();
                /*this.description = STRINGS.DESCRIPTIONS[2];*/
            }
        } else {
            /*this.grayscale = true;*/
            this.curse = false;
            this.usedUp();
            /*this.description = STRINGS.DESCRIPTIONS[2];*/
        }
    }

    public CorruptedArtifact(){
        super(BASE_ID,new Texture("img/relics/CorruptedArtifact.png"),RelicTier.RARE,LandingSound.SOLID);
    }


    @Override
    public void onEquip() {
        this.flash();
        AbstractDungeon.player.gainGold(gold);
        this.curse = true;
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (curse){
            damageAmount *= 2;
        }
        return super.onAttackedToChangeDamage(info, damageAmount);
    }

    @Override
    public void onVictory() {
        if (curse){
            this.flash();
            /*this.grayscale = true;*/
            this.usedUp();
        }
        this.curse = false;
        /*this.description = STRINGS.DESCRIPTIONS[2];*/
        super.onVictory();
    }

    @Override
    public String getUpdatedDescription() {
        return STRINGS.DESCRIPTIONS[0] + gold + STRINGS.DESCRIPTIONS[1];
    }

    @Override
    public boolean canSpawn() {
        return ((Settings.isEndless || AbstractDungeon.floorNum <= 48) &&
        !(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.ShopRoom));
    }
}
