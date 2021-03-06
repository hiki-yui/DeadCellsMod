package deadCellsMod.cn.infinite.stsmod;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
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
import deadCellsMod.cn.infinite.stsmod.monster.Zombie;
import deadCellsMod.cn.infinite.stsmod.utils.Keywords;
import deadCellsMod.cn.infinite.variables.AmmunitionVariables;
import deadCellsMod.cn.infinite.variables.BurnsVariable;
import deadCellsMod.cn.infinite.variables.ChangeNumVariables;
import deadCellsMod.cn.infinite.variables.HeavyDamageVariables;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import static basemod.DevConsole.logger;

@SpireInitializer
public class DeadCellsModInitializer implements EditCardsSubscriber,
        EditCharactersSubscriber, EditStringsSubscriber,
        EditRelicsSubscriber, EditKeywordsSubscriber, PostExhaustSubscriber,
        PostBattleSubscriber, PostDungeonInitializeSubscriber,AddAudioSubscriber,
        PostPowerApplySubscriber,OnPowersModifiedSubscriber,PostInitializeSubscriber,OnStartBattleSubscriber
        /*BaseMod.SaveCustomReward*/ {


    public DeadCellsModInitializer() {
        BaseMod.subscribe(this);
        logger.info("????????????DeadCellsMod_IMG");
        String attack_bgURL = "img/card_bg/512/attack_bg.png";
        String power_bgURL = "img/card_bg/512/power_bg.png";
        String skill_bgURL = "img/card_bg/512/skill_bg.png";
        String energy_orbUrl = "img/card_bg/512/energy_orb.png";
        String energy_smallUrl = "img/card_bg/512/energy_small.png";
        String attack_bgURL1024 = "img/card_bg/1024/attack_bg.png";
        String power_bgURL1024 = "img/card_bg/1024/power_bg.png";
        String skill_bgURL1024 = "img/card_bg/1024/skill_bg.png";
        String energy_orbUrl1024 = "img/card_bg/1024/energy_orb.png";
        logger.info("??????");
        BaseMod.addColor(AbstractDeadCellsEnum.DEAD_CELLS, Color.MAGENTA,attack_bgURL,skill_bgURL,power_bgURL,
                energy_orbUrl,attack_bgURL1024,skill_bgURL1024,power_bgURL1024,"img/card_bg/1024/energy_orb.png",energy_smallUrl);
        /*//??????????????????????????????????????????
        BaseMod.publishAddAudio(CardCrawlGame.sound);*/

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
        logger.info("????????????DeadCellsMod_audio");
        //addAudio????????????????????????????????????jar???????????????,??????????????????????????????????????????????????????????????????????????????????????????
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
        BaseMod.addStrongMonsterEncounter(Exordium.ID,new MonsterInfo("deadCells:Zombie",7));
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
        addCard(new Pyrotechnics());
        addCard(new Pyrotechnics.PyrotechnicsII());
        addCard(new Pyrotechnics.PyrotechnicsIII());
        addCard(new Pyrotechnics.PyrotechnicsIIII());
        addCard(new InsightInto());
        addCard(new IceArmor());
        addCard(new IceCrossbow());
        addCard(new IceCrossbow.IceCrossbowII());
        addCard(new IceCrossbow.IceCrossbowIII());
        addCard(new IceCrossbow.IceCrossbowIIII());
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

    }

    @Override
    public void receiveEditKeywords() {
        //????????????
       /* BaseMod.addKeyword(new String[]{"??????"},"?????????????????????????????? #y????????????????????? ??? ?????? #r????????????????????????");
        BaseMod.addKeyword(new String[]{"????????????"},"???????????????????????? #y??????");
        BaseMod.addKeyword(new String[]{"????????????"},"#r???????????????????????? ?????????????????????????????? #b??????????????????");
        BaseMod.addKeyword(new String[]{"??????"},"????????????????????? #b15% ??????????????????????????????????????????????????????????????????????????????");
        BaseMod.addKeyword(new String[]{"??????"},"????????????????????? #b15% ???????????????????????????????????????/3???????????????????????????????????????????????????????????????????????????");
        BaseMod.addKeyword(new String[]{"????????????"},"??????????????????????????????????????????");
        BaseMod.addKeyword(new String[]{"??????????????????"},"???????????????????????????????????????");
        BaseMod.addKeyword(new String[]{"??????"},"?????????????????????????????????????????????");
        BaseMod.addKeyword(new String[]{"?????????"},"?????????????????????");
        BaseMod.addKeyword(new String[]{"??????"},"?????????????????????");
        BaseMod.addKeyword(new String[]{"???"},"???????????????????????????????????????");
        BaseMod.addKeyword(new String[]{"??????"},"????????????????????? #b20% ");*/
        String lang = checkLanguage();
        FileHandle h = Gdx.files.internal("Strings/deadCells/"+lang+"/keywords.json");
        String s = h.readString(StandardCharsets.UTF_8.toString());
        Gson gson = new Gson();
        Keywords keywords = gson.fromJson(s, Keywords.class);
        System.out.println("??????????????????????????????????????????????????????addKeyword??????????????????????????????????????????????????????????????????");
        System.out.println(keywords);
        for (Keyword keyword : keywords.keywords){
            BaseMod.addKeyword(keyword.NAMES,keyword.DESCRIPTION);
        }
        System.out.println("??????????????????????????????????????????????????????addKeywordFinish??????????????????????????????????????????????????????????????????");
    }

    @Override
    public void receivePostPowerApplySubscriber(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {

    }


    public static String checkLanguage(){
        String lang = Settings.language.toString();
        if(!"ZHS".equals(lang)&&!"ENG".equals(lang)){
            lang="ENG";
        }
        return lang.toLowerCase();
    }
}
