package deadCellsMod.cn.infinite.stsmod;

import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.MonsterInfo;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import deadCellsMod.cn.infinite.stsmod.Relics.*;
import deadCellsMod.cn.infinite.stsmod.cards.*;
import deadCellsMod.cn.infinite.stsmod.character.King;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.enums.DeadCellsCharacterEnum;
import deadCellsMod.cn.infinite.stsmod.enums.DeadCellsTags;
import deadCellsMod.cn.infinite.stsmod.event.DarkSoulEvent;
import deadCellsMod.cn.infinite.stsmod.event.HalfLifeEvent;
import deadCellsMod.cn.infinite.stsmod.monster.TheGiant;
import deadCellsMod.cn.infinite.stsmod.monster.Zombie;
import deadCellsMod.cn.infinite.stsmod.utils.Keywords;
import deadCellsMod.cn.infinite.variables.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;


import static basemod.DevConsole.logger;

@SpireInitializer
public class DeadCellsModInitializer implements EditCardsSubscriber,
        EditCharactersSubscriber, EditStringsSubscriber,
        EditRelicsSubscriber, EditKeywordsSubscriber, PostExhaustSubscriber,
        PostBattleSubscriber, PostDungeonInitializeSubscriber,AddAudioSubscriber,
        PostPowerApplySubscriber,OnPowersModifiedSubscriber,PostInitializeSubscriber,OnStartBattleSubscriber
        /*BaseMod.SaveCustomReward*/ {

    public static final String RED_ATTACK_CARD = "img/card_bg/512/bg_red_attack_dc.png";
    public static final String RED_SKILL_CARD = "img/card_bg/512/bg_red_skill_dc.png";
    public static final String RED_POWER_CARD = "img/card_bg/512/bg_red_power_dc.png";

    public static final String GREEN_ATTACK_CARD = "img/card_bg/512/bg_green_attack_dc.png";
    public static final String GREEN_SKILL_CARD = "img/card_bg/512/bg_green_skill_dc.png";
    public static final String GREEN_POWER_CARD = "img/card_bg/512/bg_green_power_dc.png";

    public static final String PURPLE_ATTACK_CARD = "img/card_bg/512/bg_purple_attack_dc.png";
    public static final String PURPLE_SKILL_CARD = "img/card_bg/512/bg_purple_skill_dc.png";
    public static final String PURPLE_POWER_CARD = "img/card_bg/512/bg_purple_power_dc.png";

    public static final String GRAY_ATTACK_CARD = "img/card_bg/512/bg_gray_attack_dc.png";
    public static final String GRAY_SKILL_CARD = "img/card_bg/512/bg_gray_skill_dc.png";
    public static final String GRAY_POWER_CARD = "img/card_bg/512/bg_gray_power_dc.png";

    public static final String GOLD_ATTACK_CARD = "img/card_bg/512/bg_gold_attack_dc.png";
    public static final String GOLD_SKILL_CARD = "img/card_bg/512/bg_gold_skill_dc.png";
    public static final String GOLD_POWER_CARD = "img/card_bg/512/bg_gold_power_dc.png";

    public static final String RED2_PURPLE2_ATTACK_CARD = "img/card_bg/512/bg_red2_purple2_attack_dc.png";
    public static final String RED2_PURPLE2_SKILL_CARD = "img/card_bg/512/bg_red2_purple2_skill_dc.png";
    public static final String RED2_PURPLE2_POWER_CARD = "img/card_bg/512/bg_red2_purple2_power_dc.png";

    public static final String RED2_GREEN2_ATTACK_CARD = "img/card_bg/512/bg_red2_green2_attack_dc.png";
    public static final String RED2_GREEN2_SKILL_CARD = "img/card_bg/512/bg_red2_green2_skill_dc.png";
    public static final String RED2_GREEN2_POWER_CARD = "img/card_bg/512/bg_red2_green2_power_dc.png";

    public static final String PURPLE2_GREEN2_ATTACK_CARD = "img/card_bg/512/bg_purple2_green2_attack_dc.png";
    public static final String PURPLE2_GREEN2_SKILL_CARD = "img/card_bg/512/bg_purple2_green2_skill_dc.png";
    public static final String PURPLE2_GREEN2_POWER_CARD = "img/card_bg/512/bg_purple2_green2_power_dc.png";

    public static final String energy_orbUrl = "img/card_bg/512/energy_orb.png";
    public static final String energy_smallUrl = "img/card_bg/512/energy_small.png";

    public static final String RED_ATTACK_CARD_PORTRAIT = "img/card_bg/1024/bg_red_attack_dc_p.png";
    public static final String RED_SKILL_CARD_PORTRAIT = "img/card_bg/1024/bg_red_skill_dc_p.png";
    public static final String RED_POWER_CARD_PORTRAIT = "img/card_bg/1024/bg_red_power_dc_p.png";

    public static final String GREEN_ATTACK_CARD_PORTRAIT = "img/card_bg/1024/bg_green_attack_dc_p.png";
    public static final String GREEN_SKILL_CARD_PORTRAIT = "img/card_bg/1024/bg_green_skill_dc_p.png";
    public static final String GREEN_POWER_CARD_PORTRAIT = "img/card_bg/1024/bg_green_power_dc_p.png";

    public static final String PURPLE_ATTACK_CARD_PORTRAIT = "img/card_bg/1024/bg_purple_attack_dc_p.png";
    public static final String PURPLE_SKILL_CARD_PORTRAIT = "img/card_bg/1024/bg_purple_skill_dc_p.png";
    public static final String PURPLE_POWER_CARD_PORTRAIT = "img/card_bg/1024/bg_purple_power_dc_p.png";

    public static final String GRAY_ATTACK_CARD_PORTRAIT = "img/card_bg/1024/bg_gray_attack_dc_p.png";
    public static final String GRAY_SKILL_CARD_PORTRAIT = "img/card_bg/1024/bg_gray_skill_dc_p.png";
    public static final String GRAY_POWER_CARD_PORTRAIT = "img/card_bg/1024/bg_gray_power_dc_p.png";

    public static final String GOLD_ATTACK_CARD_PORTRAIT = "img/card_bg/1024/bg_gold_attack_dc_p.png";
    public static final String GOLD_SKILL_CARD_PORTRAIT = "img/card_bg/1024/bg_gold_skill_dc_p.png";
    public static final String GOLD_POWER_CARD_PORTRAIT = "img/card_bg/1024/bg_gold_power_dc_p.png";

    public static final String RED2_PURPLE2_ATTACK_CARD_PORTRAIT = "img/card_bg/1024/bg_red2_purple2_attack_dc_p.png";
    public static final String RED2_PURPLE2_SKILL_CARD_PORTRAIT= "img/card_bg/1024/bg_red2_purple2_skill_dc_p.png";
    public static final String RED2_PURPLE2_POWER_CARD_PORTRAIT= "img/card_bg/1024/bg_red2_purple2_power_dc_p.png";

    public static final String RED2_GREEN2_ATTACK_CARD_PORTRAIT = "img/card_bg/1024/bg_red2_green2_attack_dc_p.png";
    public static final String RED2_GREEN2_SKILL_CARD_PORTRAIT = "img/card_bg/1024/bg_red2_green2_skill_dc_p.png";
    public static final String RED2_GREEN2_POWER_CARD_PORTRAIT = "img/card_bg/1024/bg_red2_green2_power_dc_p.png";

    public static final String PURPLE2_GREEN2_ATTACK_CARD_PORTRAIT = "img/card_bg/1024/bg_purple2_green2_attack_dc_p.png";
    public static final String PURPLE2_GREEN2_SKILL_CARD_PORTRAIT = "img/card_bg/1024/bg_purple2_green2_skill_dc_p.png";
    public static final String PURPLE2_GREEN2_POWER_CARD_PORTRAIT = "img/card_bg/1024/bg_purple2_green2_power_dc_p.png";

    public static final String energy_orbUrl1024 = "img/card_bg/1024/energy_orb.png";

    public static String MAIN_MENU_CHANGE_TEXT = "Change main menu images ? (Restart to take effect)\n是否修改游戏原主界面？（重启后生效）";
    public static boolean isMainMenuImg = true;
    public static Properties MainMenuChanged = new Properties();
    private static final String MOD_BADGE = "img/badge.png";

    public DeadCellsModInitializer() {
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
        BaseMod.addColor(AbstractDeadCellsEnum.DEAD_CELLS, Color.MAGENTA,attack_bgURL,skill_bgURL,power_bgURL,
                energy_orbUrl,attack_bgURL1024,skill_bgURL1024,power_bgURL1024,"img/card_bg/1024/energy_orb.png",energy_smallUrl);
        /*//将音频发布到游戏的音频储存区
        BaseMod.publishAddAudio(CardCrawlGame.sound);*/

        MainMenuChanged.setProperty(MAIN_MENU_CHANGE_TEXT, "TRUE");
        try {
            SpireConfig config = new SpireConfig("DCMod", "DCModConfig", MainMenuChanged); // ...right here
            // the "fileName" parameter is the name of the file MTS will create where it will save our setting.
            config.load(); // Load the setting and set the boolean to equal it
            isMainMenuImg = config.getBool(MAIN_MENU_CHANGE_TEXT);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void initialize(){
        new DeadCellsModInitializer();
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        for (AbstractCard card : AbstractDungeon.player.masterDeck.group){
            if (card instanceof CursedSword){
                for (int i = 0;i<card.baseMagicNumber;i++){
                    AbstractCard curse = AbstractDungeon.curseCardPool.group.get(AbstractDungeon.cardRng.random(AbstractDungeon.curseCardPool.group.size()-1));
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(curse,1,true,true,false));/*new ShowCardAndAddToDrawPileEffect(curse,true,false));*/
                }
            }
        }
    }

    /*@Override
    public RewardSave onSave(CustomReward customReward) {
*//*
        return new RewardSave("CARD",Abstract);
*//*
    }*/

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
        BaseMod.addAudio("deadCells:THROWING_KNIFE","audio/sound/ThrowingKnife.ogg");
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        if (AbstractDungeon.player.hasPower("deadCells:NightLightPower")) {
            for (int i = 0;i<AbstractDungeon.player.getPower("deadCells:NightLightPower").amount;i++) {
                RewardItem item = new RewardItem();
                abstractRoom.addCardReward(item);
                /*AbstractDungeon.combatRewardScreen.rewards.add(item);*/
                /*CardCrawlGame.saveFile.combat_rewards.add(new RewardSave("CARD","deadCells:NightLight"));*/
            }
        }
        if (AbstractDungeon.player.hasPower("deadCells:RobPower")) {
           abstractRoom.addGoldToRewards(AbstractDungeon.player.getPower("deadCells:RobPower").amount);
        }
    }


    @Override
    public void receivePostDungeonInitialize() {
        /*BaseMod.registerCustomReward(RewardItem.RewardType.CARD,);*/
    }

    @Override
    public void receivePostInitialize() {
        BaseMod.addMonster( "deadCells:Zombie",() -> new Zombie(0.0F, 0.0F));
        //BaseMod.addMonster("deadCells:TheGiant", () -> new TheGiant());
        BaseMod.addStrongMonsterEncounter(Exordium.ID,new MonsterInfo("deadCells:Zombie",7));
        //BaseMod.addBoss(TheBeyond.ID,TheGiant.BASE_ID,"img/monster/guardian_o.png","img/monster/guardian.png");

        BaseMod.addEvent(DarkSoulEvent.ID, DarkSoulEvent.class);
        BaseMod.addEvent(HalfLifeEvent.ID, HalfLifeEvent.class);
        //Mod Settings Screen
        final ModPanel settingsPanel = new ModPanel();

        //修改主界面
        final ModLabeledToggleButton MainMenuChangeButton =
                new ModLabeledToggleButton(MAIN_MENU_CHANGE_TEXT,
                        400,
                        660,
                        Settings.CREAM_COLOR,
                        FontHelper.charDescFont,
                        isMainMenuImg,
                        settingsPanel,
                        (label) -> {},
                        (button) -> {isMainMenuImg = button.enabled;
                            try {
                                // And based on that boolean, set the settings and save them
                                SpireConfig config = new SpireConfig("DCMod", "DCModConfig", MainMenuChanged );
                                config.setBool(MAIN_MENU_CHANGE_TEXT, isMainMenuImg);
                                config.save();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                );
        settingsPanel.addUIElement(MainMenuChangeButton);

        final Texture badge = ImageMaster.loadImage(MOD_BADGE);
        BaseMod.registerModBadge(
                badge,
                "Dead Cells Mod(死亡细胞）",
                "hiki,Mamlot,CSTG工具寅",
                "啦啦啦~",
                settingsPanel
        );
    }

    @Override
    public void receivePostExhaust(AbstractCard abstractCard) {

    }

    public static final ArrayList<AbstractCard> GRENADE_POOL = new ArrayList<>();
    public static final ArrayList<AbstractCard> SHIELD_POOL = new ArrayList<>();

    private void addCard(AbstractCard card){
        BaseMod.addCard(card);
        if (card.tags.contains(DeadCellsTags.GRENADE)){
            GRENADE_POOL.add(card);
        }
        if (card.tags.contains(DeadCellsTags.SHIELD)){
            SHIELD_POOL.add(card);
        }
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new BurnsVariable());
        BaseMod.addDynamicVariable(new AmmunitionVariables());
        BaseMod.addDynamicVariable(new HeavyDamageVariables());
        BaseMod.addDynamicVariable(new ChangeNumVariables());
        BaseMod.addDynamicVariable(new AmmunitionVariables.MaxAmmunitionVariables());
        BaseMod.addDynamicVariable(new AmNumberVariable());
        addCard(new SymmetricalSpear());
        addCard(new Strike_king());
        addCard(new Defend_king());
        addCard(new EmergencyDoor());
        addCard(new Saucepan());
        addCard(new Roll());
        addCard(new Roll_ButNot());
        addCard(new Piano());
        addCard(new PianoII());
        addCard(new PianoIII());
        addCard(new AlchemyCarbine());
        addCard(new CrowFeathers());
        addCard(new RoundingKnife());
        addCard(new Speed());
        addCard(new Grenade());
        addCard(new DepravedForm());
        addCard(new StunGrenade());
        addCard(new BalancedBlade());
        addCard(new ReflectionSpeedUp());
        addCard(new Rampart());
        addCard(new IceShield());
        addCard(new ScytheClaw());
        addCard(new ScytheClawII());
        addCard(new SpeedDown());
        addCard(new StrategyStagnated());
        addCard(new MagicMissiles());
        addCard(new BloodSword());
        addCard(new BleedingSpread());
        addCard(new BloodthirstyShield());
        addCard(new ThrowingKnife());
        addCard(new AttackDefend());
        addCard(new InASimilar());
        addCard(new IceGrenade());
        addCard(new FillInGrenade());
        addCard(new RootGrenade());
        addCard(new FireGrenade());
        addCard(new MagneticGrenade());
        addCard(new Backtrack());
        addCard(new SadistStiletto());
        addCard(new Torch());
        addCard(new Firebrands());
        addCard(new InsightInto());
        addCard(new IceArmor());
        addCard(new OilGrenade());
        addCard(new OiledSword());
        addCard(new SwiftSword());
        addCard(new Toothpick());
        addCard(new Toothpick.ToothpickII());
        addCard(new Toothpick.ToothpickIII());
        addCard(new Toothpick.ToothpickIIII());
        addCard(new APlan());
        addCard(new FireBlast());
        addCard(new FrostBlast());
        addCard(new RegularSkill());
        addCard(new Calm());
        addCard(new FerrymanSLantern());
        addCard(new FerrymanSLantern.FerrymanSLanternII());
        addCard(new FerrymanSLantern.FerrymanSLanternIII());
        addCard(new HattoriSKatana());
        addCard(new SOTF());
        addCard(new SOTF.StrengthPowerII());
        addCard(new SOTF.DexterityPowerII());
        addCard(new Hemorrhage());
        addCard(new FrontLineShield());
        addCard(new Ruthless());
        addCard(new KnockBackShield());
        addCard(new CorrosiveCloud());
        addCard(new Reckless());
        addCard(new LaceratingAura());
        addCard(new SmokeBomb());
        addCard(new ClusterGrenade());
        addCard(new SnakeFangs());
        addCard(new IronStaff());
        addCard(new CursedSword());
        addCard(new FireworksTechnician());
        addCard(new HayabusaBoots());
        addCard(new Cocoon());
        addCard(new Vampirism());
        addCard(new FranticSword());
        addCard(new HayabusaGauntlets());
        addCard(new Punishment());
        addCard(new ThunderShield());
        addCard(new InTime());
        addCard(new Giantkiller());
        addCard(new NightLight());
        addCard(new Rob());
        addCard(new BeginnerBow());
        addCard(new MultipleNocksBow());
        addCard(new IceBow());
        addCard(new SonicCarbine());
        addCard(new KillingDeck());
        addCard(new GiantWhistle());
        addCard(new CrowBar());
        addCard(new LightningBolt());
       /* File file = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                .getResource("deadCellsMod/cn/infinite/stsmod/cards/")).getFile());*/
        /*try {
            String jarPath = Test.class.getProtectionDomain()
                    .getCodeSource().getLocation().getPath();
            File file = new File(jarPath.substring(0,jarPath.length()-4)+"/deadCellsMod/cn/infinite/stsmod/cards/");
            System.out.println(file);
           *//* int firstIndex = jarPath.lastIndexOf(System.getProperty("path.separator")) + 1;
            int lastIndex = jarPath.lastIndexOf(File.separator) + 1;
            jarPath = jarPath.substring(firstIndex, lastIndex);*//*
            for (File f : Objects.requireNonNull(file.listFiles())){
                    Class c = Class.forName("deadCellsMod.cn.infinite.stsmod.cards."+f.getName().substring(0,f.getName().length()-6));
                    System.out.println(c.newInstance().toString());
                    addCard((AbstractCard)c.newInstance());
                }
        } catch ( ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
*/
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
        addRelics(new OpenWounds());
        BaseMod.addRelic(new Predator(),RelicType.SHARED);
        /*BaseMod.addRelic(new BackPack_sk(),RelicType.SHARED);
        BaseMod.addRelic(new BackPack_at(),RelicType.SHARED);*/
        BaseMod.addRelic(new Nocturne(), RelicType.SHARED);
        BaseMod.addRelic(new Chopper(), RelicType.SHARED);
        BaseMod.addRelic(new Counterattack(),RelicType.SHARED);
        BaseMod.addRelic(new SealsRecipe(),RelicType.SHARED);
        BaseMod.addRelic(new Alive(),RelicType.SHARED);
        BaseMod.addRelic(new Oppressive(),RelicType.SHARED);
        BaseMod.addRelic(new Tactical(),RelicType.SHARED);
        BaseMod.addRelic(new PowerOfScroll(),RelicType.SHARED);
        BaseMod.addRelic(new CorruptedArtifact(),RelicType.SHARED);
        BaseMod.addRelic(new Defender(),RelicType.SHARED);
        BaseMod.addRelic(new RamRune(), RelicType.SHARED);
    }

    private void addRelics(AbstractRelic relic){
        BaseMod.addRelicToCustomPool(relic, AbstractDeadCellsEnum.DEAD_CELLS);
    }

    @Override
    public void receivePowersModified() {
        /*BaseMod.addPower(NextTurnLoseFlightPower.class,"deadCells:NextTurnLoseFlightPower");*/
    }

    @Override
    public void receiveEditStrings() {

        String lang = checkLanguage();
        BaseMod.loadCustomStringsFile(CardStrings.class, "Strings/deadCells/"+lang+"/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "Strings/deadCells/"+lang+"/character.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "Strings/deadCells/"+lang+"/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "Strings/deadCells/"+lang+"/powers.json");
        BaseMod.loadCustomStringsFile(MonsterStrings.class, "Strings/deadCells/"+lang+"/monsters.json");
        BaseMod.loadCustomStringsFile(EventStrings.class, "Strings/deadCells/"+lang+"/event.json");
    }

    @Override
    public void receiveEditKeywords() {
        //以后再改
       /* BaseMod.addKeyword(new String[]{"流血"},"在一张牌被使用后受到 #y层数一半的伤害 ， 层数 #r减少对应的伤害值");
        BaseMod.addKeyword(new String[]{"夜歌标记"},"受到的下一次伤害 #y翻倍");
        BaseMod.addKeyword(new String[]{"合适的牌"},"#r只适用于当前回合 的牌，因为很适合所以 #b费用会减少一");
        BaseMod.addKeyword(new String[]{"冻伤"},"造成的伤害减少 #b15% ，在此基础上再减少对应层数的伤害，回合结束时层数减一");
        BaseMod.addKeyword(new String[]{"燃烧"},"受到的伤害增加 #b15% ，在此基础上再增加对应层数/3的伤害，回合结束时受到对应层数的伤害，失去所有燃烧");
        BaseMod.addKeyword(new String[]{"藤蔓缠绕"},"回合开始时受到对应层数的伤害");
        BaseMod.addKeyword(new String[]{"选择加入手牌"},"将这张卡的另一形态加入手牌");
        BaseMod.addKeyword(new String[]{"弹药"},"打出某些牌时需要消耗一定的弹药");
        BaseMod.addKeyword(new String[]{"未蓄力"},"消耗为一的形态");
        BaseMod.addKeyword(new String[]{"蓄力"},"消耗为二的形态");
        BaseMod.addKeyword(new String[]{"油"},"带有油的单位受到的燃烧翻倍");
        BaseMod.addKeyword(new String[]{"隐匿"},"受到的伤害减少 #b20% ");*/
        String lang = checkLanguage();
        FileHandle h = Gdx.files.internal("Strings/deadCells/"+lang+"/keywords.json");
        String s = h.readString(StandardCharsets.UTF_8.toString());
        Gson gson = new Gson();
        Keywords keywords = gson.fromJson(s, Keywords.class);
        System.out.println("——————————————————addKeyword——————————————————————");
        System.out.println(keywords);
        for (Keyword keyword : keywords.keywords){
            BaseMod.addKeyword(keyword.NAMES,keyword.DESCRIPTION);
        }
        System.out.println("——————————————————addKeywordFinish——————————————————————");
    }

    @Override
    public void receivePostPowerApplySubscriber(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {

    }


    public static String checkLanguage(){
        String lang = Settings.language.toString();
        if(!"ZHS".equals(lang)&&!"ENG".equals(lang)&&!"FRA".equals(lang)){
            lang="ENG";
        }
        return lang.toLowerCase();
    }
}
