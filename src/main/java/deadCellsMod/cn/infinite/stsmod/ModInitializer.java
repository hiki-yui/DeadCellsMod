package deadCellsMod.cn.infinite.stsmod;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import deadCellsMod.cn.infinite.stsmod.Relics.*;
import deadCellsMod.cn.infinite.stsmod.cards.*;
import deadCellsMod.cn.infinite.stsmod.character.King;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;
import deadCellsMod.cn.infinite.stsmod.enums.DeadCellsCharacterEnum;

import static basemod.DevConsole.logger;

@SpireInitializer
public class ModInitializer implements EditCardsSubscriber,
        EditCharactersSubscriber, EditStringsSubscriber,
        EditRelicsSubscriber, EditKeywordsSubscriber, PostExhaustSubscriber,
        PostBattleSubscriber, PostDungeonInitializeSubscriber,AddAudioSubscriber,
        PostPowerApplySubscriber,OnPowersModifiedSubscriber{

    public ModInitializer() {
        BaseMod.subscribe(this);
        logger.info("开始载入DeadCellsMod_IMG");
        String attack_bgURL = "img/card_bg/512/attack_bg.png";
        String power_bgURL = "img/card_bg/512/power_bg.png";
        String skill_bgURL = "img/card_bg/512/skill_bg.png";
        String energy_orbUrl = "img/card_bg/512/energy_orb.png";
        String energy_smallUrl = "img/card_bg/512/energy_small.png";
        String attack_bgURL1024 = "img/card_bg/1024/attack_bg.png";
        String power_bgURL1024 = "img/card_bg/1024/power_bg.png";
        String skill_bgURL1024 = "img/card_bg/1024/skill_bg.png";
        String energy_orbUrl1024 = "img/card_bg/1024/energy_orb.png";
        logger.info("完成");
        BaseMod.addColor(AbstractCardEnum.DEAD_CELLS, Color.MAGENTA,attack_bgURL,skill_bgURL,power_bgURL,
                energy_orbUrl,attack_bgURL1024,skill_bgURL1024,power_bgURL1024,"img/card_bg/1024/energy_orb.png",energy_smallUrl);
        /*//将音频发布到游戏的音频储存区
        BaseMod.publishAddAudio(CardCrawlGame.sound);*/

    }

    public static void initialize(){
        new ModInitializer();
    }

    @Override
    public void receiveAddAudio() {
        logger.info("开始载入DeadCellsMod_audio");
        //addAudio方法是将音频文件放到自身jar包的缓存区,想要使用文件还需要将缓存区中的内容发布到游戏的音频储存区才行
        BaseMod.addAudio("deadCells:PIANO", "audio/sound/Piano.ogg");
        BaseMod.addAudio("deadCells:PIANO_II", "audio/sound/PianoII.ogg");
        BaseMod.addAudio("deadCells:PIANO_III", "audio/sound/PianoIII.ogg");
        BaseMod.addAudio("deadCells:ROLL", "audio/sound/Roll.ogg");
        BaseMod.addAudio("deadCells:PARRY", "audio/sound/Parry.ogg");
        BaseMod.addAudio("deadCells:ALCHEMY_CARBINE", "audio/sound/AlchemyCarbine.ogg");
        BaseMod.addAudio("deadCells:SYMMETRICAL_SPEARS_III", "audio/sound/SymmetricalSpearIII.ogg");
        BaseMod.addAudio("deadCells:SYMMETRICAL_SPEARS_II", "audio/sound/SymmetricalSpearII.ogg");
        BaseMod.addAudio("deadCells:SYMMETRICAL_SPEARS_I", "audio/sound/SymmetricalSpearI.ogg");
        BaseMod.addAudio("deadCells:ROUNDING_KNIFE","audio/sound/RoundingKnife.ogg");
        BaseMod.addAudio("deadCells:SPEED","audio/sound/Speed.ogg");
        BaseMod.addAudio("deadCells:DEPRAVED_FORM","audio/sound/DepravedForm.ogg");
        BaseMod.addAudio("deadCells:SCYTHE_CLAW","audio/sound/ScytheClaw.ogg");
        BaseMod.addAudio("deadCells:SCYTHE_CLAW_II","audio/sound/ScytheClawII.ogg");
        BaseMod.addAudio("deadCells:MAGIC_MISSILES","audio/sound/MagicMissiles.ogg");
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {

    }

    @Override
    public void receivePostDungeonInitialize() {

    }

    @Override
    public void receivePostExhaust(AbstractCard abstractCard) {

    }

    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new SymmetricalSpear());
        BaseMod.addCard(new Strike_king());
        BaseMod.addCard(new Defend_king());
        BaseMod.addCard(new EmergencyDoor());
        BaseMod.addCard(new Saucepan());
        BaseMod.addCard(new Roll());
        BaseMod.addCard(new Roll_ButNot());
        BaseMod.addCard(new Piano());
        BaseMod.addCard(new PianoII());
        BaseMod.addCard(new PianoIII());
        BaseMod.addCard(new AlchemyCarbine());
        BaseMod.addCard(new CrowFeathers());
        BaseMod.addCard(new RoundingKnife());
        BaseMod.addCard(new Speed());
        BaseMod.addCard(new Grenade());
        BaseMod.addCard(new DepravedForm());
        BaseMod.addCard(new StunGrenade());
        BaseMod.addCard(new BalancedBlade());
        BaseMod.addCard(new ReflectionSpeedUp());
        BaseMod.addCard(new Rampart());
        BaseMod.addCard(new IceShield());
        BaseMod.addCard(new ScytheClaw());
        BaseMod.addCard(new ScytheClawII());
        BaseMod.addCard(new SpeedDown());
        BaseMod.addCard(new StrategyStagnated());
        BaseMod.addCard(new MagicMissiles());
        BaseMod.addCard(new BloodSword());
        BaseMod.addCard(new BleedingSpread());
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new King(),
                "img/char_select/king.png",
                "img/char_portraits/kingPortrait.png",
                DeadCellsCharacterEnum.DeadCells);
    }

    @Override
    public void receiveEditRelics() {
        addRelics(new EmeraldAmulet());
        addRelics(new BackPack_sk());
        addRelics(new BackPack_at());
        addRelics(new SpirePace());
        /*BaseMod.addRelic(new BackPack_sk(),RelicType.SHARED);
        BaseMod.addRelic(new BackPack_at(),RelicType.SHARED);*/
        BaseMod.addRelic(new Nocturne(), RelicType.SHARED);
        BaseMod.addRelic(new Chopper(), RelicType.SHARED);
        BaseMod.addRelic(new Counterattack(),RelicType.SHARED);
    }

    private void addRelics(AbstractRelic relic){
        BaseMod.addRelicToCustomPool(relic,AbstractCardEnum.DEAD_CELLS);
    }
    @Override
    public void receivePowersModified() {
        /*BaseMod.addPower(NextTurnLoseFlightPower.class,"deadCells:NextTurnLoseFlightPower");*/
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class,"Strings/deadCells/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class,"Strings/deadCells/character.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class,"Strings/deadCells/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class,"Strings/deadCells/powers.json");
        BaseMod.loadCustomStringsFile(KeywordStrings.class,"Strings/deadCells/keywords.json");
    }

    @Override
    public void receiveEditKeywords() {
        BaseMod.addKeyword(new String[]{"流血"},"在一张牌被使用后受到层数一半的伤害,层数对应的伤害值");
        BaseMod.addKeyword(new String[]{"夜歌标记"},"受到的下一次伤害翻倍");
        BaseMod.addKeyword(new String[]{"合适的牌"},"只适用于当前回合的牌，因为很适合所以费用会减少一");
    }

    @Override
    public void receivePostPowerApplySubscriber(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {

    }



}
