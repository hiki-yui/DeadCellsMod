package deadCellsMod.cn.infinite.stsmod.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.StarBounceEffect;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;

public class Chopper extends CustomRelic {
    public static final String BASE_ID = "deadCells:Chopper";
    private static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(BASE_ID);
    private static final String IMG = "img/relics/Chopper.png";

    public Chopper() {
        super(BASE_ID, new Texture(IMG), RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return STRINGS.DESCRIPTIONS[0] + 15 + STRINGS.DESCRIPTIONS[1];
    }

    @Override
    public void atTurnStart() {
        pulse();
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        pulse();
    }

    private void pulse(){
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters){
            if (monster.currentHealth <= monster.maxHealth*0.15F && !(monster.isDead)){
                beginLongPulse();
            }
        }
    }
    @Override
    public void onPlayerEndTurn() {
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters){
            if (!monster.isDead){
                if (monster.currentHealth<=monster.maxHealth*0.15F){
                    this.flash();
                    addToBot(new RelicAboveCreatureAction(monster,this));
                    this.addToBot(new VFXAction(new ViolentAttackEffect(monster.hb.cX, monster.hb.cY, Color.MAGENTA), 0.2F));
                    this.addToBot(new VFXAction(new StarBounceEffect(monster.hb.cX, monster.hb.cY)));
                    addToBot(new DamageAction(monster,new DamageInfo(AbstractDungeon.player,(int)(monster.maxHealth*0.15), DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_HEAVY,true));
                }
            }
        }
        this.stopPulse();
    }

    @Override
    public void onVictory() {
        stopPulse();
        super.onVictory();
    }
}