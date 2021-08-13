package deadCellsMod.cn.infinite.stsmod.character;


import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import deadCellsMod.cn.infinite.stsmod.cards.RoundingKnife;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.enums.DeadCellsCharacterEnum;

import java.util.ArrayList;
import java.util.List;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.*;

public class King extends CustomPlayer {
    public static String characterId = "deadCells:king";
    private static final CharacterStrings characterStrings =
            CardCrawlGame.languagePack.getCharacterString(characterId);
    private static final String NAME = characterStrings.NAMES[0];
    private static final int ENERGY_REP_TURN = 3;//初始能量,每回合刷新的能量数
    private static final String[] ORB_IMG = {"img/char_portraits/orb/layer1.png",
            "img/char_portraits/orb/layer2.png", "img/char_portraits/orb/layer3.png",
            "img/char_portraits/orb/layer4.png", "img/char_portraits/orb/layer5.png",
            "img/char_portraits/orb/layer6.png", "img/char_portraits/orb/layer1d.png",
            "img/char_portraits/orb/layer2d.png", "img/char_portraits/orb/layer3d.png",
            "img/char_portraits/orb/layer4d.png", "img/char_portraits/orb/layer5d.png"};
    private static final float[] layerSpeeds = {-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};

    public King() {
        super(NAME, DeadCellsCharacterEnum.DeadCells, ORB_IMG, "img/char_portraits/orb/vfx.png",
                layerSpeeds, null, null);
        this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
        this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values
        initializeClass(null, "img/char_portraits/kingShoulder2.png",
                "img/char_portraits/kingShoulder.png",
                "img/char_portraits/kingCorpse.png",
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_REP_TURN));
        loadAnimation("img/char_portraits/animation/DeadCells_normal.atlas", "img/char_portraits/animation/DeadCells_normal.json", 3.5F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.stateData.setMix("Hit", "Idle", 0.1F);
        e.setTimeScale(1.0F);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        //设置初始卡牌的方法,返回一套初始卡牌
        ArrayList<String> deck = new ArrayList<>();
        //不放在一个循环是因为要给用户提供一个良好的界面 例: 打击 打击 防御 防御
        //不然会卡组就会变成: 打击 防御 打击 防御
        for (int i = 0; i < 4; i++) {
            deck.add("king:Strike");
        }//打击防御
        for (int i = 0; i < 4; i++) {
            deck.add("king:Defend");
        }
        deck.add("deadCells:Saucepan");
        deck.add("deadCells:RoundingKnife");
        /*deck.add("deadCells:Grenade");
        deck.add("deadCells:AlchemyCarbine");
        deck.add("deadCells:SymmetricalSpear");
        deck.add("deadCells:EmergencyDoor");

        deck.add("deadCells:Roll");
        deck.add("deadCells:Piano");
        deck.add("deadCells:CrowFeathers");
        deck.add("deadCells:RoundingKnife");
        deck.add("deadCells:DepravedForm");
        deck.add("deadCells:ReflectionSpeedUp");
        deck.add("deadCells:StunGrenade");
        deck.add("deadCells:BalancedBlade");
        deck.add("deadCells:Rampart");
        deck.add("deadCells:IceShield");
        deck.add("deadCells:ScytheClaw");
        deck.add("deadCells:StrategyStagnated");
        deck.add("deadCells:MagicMissiles");
        deck.add("deadCells:BleedingSpread");
        deck.add("deadCells:BloodSword");
        deck.add("deadCells:BloodthirstyShield");
        deck.add("deadCells:AttackDefend");*/
       /* deck.add("deadCells:InASimilar");
        deck.add("deadCells:IceGrenade");*/
        return deck;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> relics = new ArrayList<>();
        relics.add("deadCells:EmeraldAmulet");
        /*relics.add("deadCells:BackPack_at");*/
        return relics;
    }

    @Override
    public CharSelectInfo getLoadout() {

        return new CharSelectInfo(this.getLocalizedCharacterName(),
                characterStrings.TEXT[0], 75, 75, 0, 99, 5, this,
                getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return "国王";
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractDeadCellsEnum.DEAD_CELLS;
    }

    @Override
    public List<CutscenePanel> getCutscenePanels() {
        List<CutscenePanel> panels = new ArrayList<>();
        panels.add(new CutscenePanel("img/char_select/endScene1.png", "ATTACK_HEAVY"));
        panels.add(new CutscenePanel("img/char_select/endScene2.png"));
        panels.add(new CutscenePanel("img/char_select/endScene3.png"));
        return panels;
    }

    @Override
    public Color getCardRenderColor() {
        return Color.MAGENTA;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new RoundingKnife();
    }

    @Override
    public Color getCardTrailColor() {
        return Color.MAGENTA;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 8;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("choose", 0.0F);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "AUTOMATON_ORB_SPAWN";
    }

    @Override
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new King();
    }

    @Override
    public String getSpireHeartText() {
        return SpireHeart.DESCRIPTIONS[10];
    }

    @Override
    public void playDeathAnimation() {
        if (AbstractDungeon.player != null) {
            AnimationState.TrackEntry e = this.state.setAnimation(0, "Death", false);
            e.setTimeScale(1.0F);
        }
    }

    @Override
    public Color getSlashAttackColor() {
        return Color.MAGENTA;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                FIRE,
                BLUNT_HEAVY,
                SLASH_DIAGONAL,
                SLASH_HEAVY,
                SLASH_VERTICAL,
                FIRE,
                BLUNT_HEAVY,
                FIRE,
                BLUNT_HEAVY,
                SLASH_DIAGONAL,
                SLASH_HEAVY,
                SLASH_VERTICAL,
                FIRE,
                BLUNT_HEAVY
        };
    }

    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[1];
    }

}

