package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.SecondWind;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import deadCellsMod.cn.infinite.stsmod.action.AddCardToHandAction;
import deadCellsMod.cn.infinite.stsmod.cards.Defend_king;
import deadCellsMod.cn.infinite.stsmod.cards.DepravedForm;
import deadCellsMod.cn.infinite.stsmod.cards.Piano;
import deadCellsMod.cn.infinite.stsmod.cards.Strike_king;
import deadCellsMod.cn.infinite.stsmod.utils.ImgUtils;

import javax.print.DocFlavor;
import java.util.*;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ReflectionSpeedUpPower extends AbstractPower {

    public static final String BASE_ID = "deadCells:ReflectionSpeedUpPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);
    private static final ArrayList<String> CAN_NOT_APPEAR = new ArrayList<>();
    private static final TreeSet<String> IS_SKILL_BUT_ATTACK = new TreeSet<>();
    private static boolean canGiveCard = false;
    private static int drawCardCounter= 0;
    private int canNotUseCard = 0;
    static{
        CAN_NOT_APPEAR.add("Hello World");
        CAN_NOT_APPEAR.add("White Noise");
        CAN_NOT_APPEAR.add("Clash");
        CAN_NOT_APPEAR.add("JustLucky");
        CAN_NOT_APPEAR.add("Havoc");
        CAN_NOT_APPEAR.add("Catalyst");
        CAN_NOT_APPEAR.add("Tactician");
        CAN_NOT_APPEAR.add("Reflex");
        CAN_NOT_APPEAR.add("Setup");
        CAN_NOT_APPEAR.add("Phantasmal Killer");
        CAN_NOT_APPEAR.add("Outmaneuver");
        CAN_NOT_APPEAR.add("Deflect");
        CAN_NOT_APPEAR.add("Gash");
        CAN_NOT_APPEAR.add("Genetic Algorithm");
        CAN_NOT_APPEAR.add("Redo");
        CAN_NOT_APPEAR.add("Amplify");
        CAN_NOT_APPEAR.add("Multi-Cast");
        CAN_NOT_APPEAR.add("Darkness");
        CAN_NOT_APPEAR.add("Fusion");
        CAN_NOT_APPEAR.add("Consume");
        CAN_NOT_APPEAR.add("Perseverance");
        CAN_NOT_APPEAR.add("WindmillStrike");
        CAN_NOT_APPEAR.add("Vengeance");
        CAN_NOT_APPEAR.add("Alpha");
        CAN_NOT_APPEAR.add("ConjureBlade");
        CAN_NOT_APPEAR.add("Limit Break");
        CAN_NOT_APPEAR.add("Barrage");
        CAN_NOT_APPEAR.add("Fission");
        CAN_NOT_APPEAR.add("deadCells:NightLight");
        CAN_NOT_APPEAR.add("deadCells:Rob");



        IS_SKILL_BUT_ATTACK.add("Tempest");
        IS_SKILL_BUT_ATTACK.add("Flex");
        IS_SKILL_BUT_ATTACK.add("Infernal Blade");
        IS_SKILL_BUT_ATTACK.add("Spot Weakness");
        IS_SKILL_BUT_ATTACK.add("Double Tap");
        IS_SKILL_BUT_ATTACK.add("Blade Dance");
        IS_SKILL_BUT_ATTACK.add("Deadly Poison");
        IS_SKILL_BUT_ATTACK.add("Bouncing Flask");
        IS_SKILL_BUT_ATTACK.add("Terror");
        IS_SKILL_BUT_ATTACK.add("Corpse Explosion");
        IS_SKILL_BUT_ATTACK.add("Storm of Steel");
        IS_SKILL_BUT_ATTACK.add("ForeignInfluence");
        IS_SKILL_BUT_ATTACK.add("Crescendo");
        IS_SKILL_BUT_ATTACK.add("Judgement");
        IS_SKILL_BUT_ATTACK.add("deadCells:FillInGrenade");
        IS_SKILL_BUT_ATTACK.add("deadCells:Pyrotechnics");
        IS_SKILL_BUT_ATTACK.add("deadCells:FerrymanSLantern");
        IS_SKILL_BUT_ATTACK.add("deadCells:APlan");
        IS_SKILL_BUT_ATTACK.add("deadCells:Toothpick");
        IS_SKILL_BUT_ATTACK.add("deadCells:Reckless");

    }

    public ReflectionSpeedUpPower(AbstractPlayer player,int amount){
        this.ID = BASE_ID;
        this.name = STRINGS.NAME;
        this.owner = player;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.updateDescription();
        ImgUtils.setPowerImg(this);
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0]+amount+STRINGS.DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        /*canGiveCard = true;*/
        addToBot(
                new AbstractGameAction() {
                    @Override
                    public void update() {
                        Map<String,Integer> map = think();
                        giveCard(map);
                        canNotUseCard = 0;
                        /*canGiveCard = false;
                        drawCardCounter = 0;*/
                        isDone = true;
                    }
                }
        );
        super.atStartOfTurn();
    }

    //算比分的方法
    public Map<String,Integer> think(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("attack", 0);
        map.put("buff", 0);
        map.put("defend", 0);
        int skillCardNum = 0;
        int attackCardNum = 0;
        int powerCard = 0;
        int powerNum = 0;

        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power.type == PowerType.BUFF) {
                powerNum++;
            } else {
                powerNum--;
            }
        }
        if (powerNum < 4) {
            System.out.println("buff数量小于4");//sout
            increase(map, "buff", 1);
        } /*else if (AbstractDungeon.player.powers.size() > 4 && powerNum < 7) {
            System.out.println("buff数量小于7但大于4");//sout
            increase(map, "buff");
        }*/ else if (powerNum > 7) {
            System.out.println("能力过多,比重减小");
            increase(map, "buff", -1);
        }

        addToBot(new WaitAction(0.5F));
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            System.out.println(card);
            if (card.type == AbstractCard.CardType.SKILL) {
                skillCardNum++;
            } else if (card.type == AbstractCard.CardType.ATTACK) {
                attackCardNum++;
                if ("Grand Finale".equals(card.cardID)) {
                    CAN_NOT_APPEAR.remove("Setup");
                } else {
                    if (!CAN_NOT_APPEAR.contains("Setup")) {
                        CAN_NOT_APPEAR.add("Setup");
                    }
                }
            } else if (card.type == AbstractCard.CardType.STATUS || card.type == AbstractCard.CardType.CURSE) {
                canNotUseCard++;
            } else {
                powerCard++;
            }
        }

        if (skillCardNum < 1) {
            System.out.println("没有技能牌");//sout
            increase(map, "defend", 2);
        } else if (skillCardNum <= 2) {
            System.out.println("技能牌数量少");//sout
            increase(map, "defend");
        }

        if (attackCardNum < 1) {
            System.out.println("没有攻击牌");//sout
            increase(map, "attack", 2);
        } else if (attackCardNum <= 2) {
            System.out.println("攻击牌数量少");//sout
            increase(map, "attack");
        }

        if (powerCard < 1) {
            System.out.println("没有能力牌");//sout
            increase(map, "buff");
        }

        int aliveMonster = 0;
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDying) {
                aliveMonster++;
                if (monster.currentHealth <= 15 && monster.intent == AbstractMonster.Intent.STRONG_DEBUFF || (monster.getPower("Split") != null && monster.currentHealth <= monster.maxHealth * 0.60F)) {
                    System.out.println("快速斩杀强力deBuff给与者");//sout
                    increase(map, "attack", 9);
                } else if (monster.intent == AbstractMonster.Intent.ESCAPE || monster.getPower("Mode Shift") != null || monster.currentHealth <= 15) {
                    System.out.println(monster.name + "已到斩杀线或处于攻击姿态");//sout
                    increase(map, "attack", 4);
                } else if (monster.getPower("Anger") != null) {
                    System.out.println("蹋蹋开！");//sout
                    increase(map, "attack", 9);
                } else {
                    if (monster.intent == AbstractMonster.Intent.ATTACK) {
                        increase(map, "defend", 2);
                    } else if (monster.intent == AbstractMonster.Intent.ATTACK_DEFEND) {
                        increase(map, "defend");
                        increase(map, "attack");
                    } else if (monster.intent == AbstractMonster.Intent.ATTACK_BUFF) {
                        increase(map, "defend");
                        increase(map, "buff");
                    } else if (monster.intent == AbstractMonster.Intent.ATTACK_DEBUFF) {
                        increase(map, "defend");
                        increase(map, "buff");
                    } else if (monster.intent == AbstractMonster.Intent.DEFEND) {
                        increase(map, "attack", 2);
                        increase(map, "buff");
                    } else if (monster.intent == AbstractMonster.Intent.DEFEND_BUFF) {
                        increase(map, "attack");
                        increase(map, "buff", 2);
                    } else if (monster.intent == AbstractMonster.Intent.DEFEND_DEBUFF) {
                        increase(map, "attack");
                        increase(map, "buff", 2);
                    } else if (monster.intent == AbstractMonster.Intent.DEBUFF || monster.intent == AbstractMonster.Intent.STUN || monster.intent == AbstractMonster.Intent.BUFF) {
                        increase(map, "buff", 2);
                    } else if (monster.intent == AbstractMonster.Intent.SLEEP) {
                        increase(map, "buff", 4);
                    } else if (monster.intent == AbstractMonster.Intent.STRONG_DEBUFF) {
                        increase(map, "buff", 3);
                    }
                }
            }
        }
        AbstractPlayer player = AbstractDungeon.player;
        int frostNum = 0;
        int orbNum = 0;
        if (player.getPower("Focus") != null && player.getPower("Focus").amount > 0) {
            if (CAN_NOT_APPEAR.contains("Reprogram")) {
                CAN_NOT_APPEAR.add("Reprogram");
            }
        } else {
            CAN_NOT_APPEAR.remove("Reprogram");
        }

        if (player.orbs != null && player.orbs.size() != 0) {
            orbNum = player.orbs.size();
            for (AbstractOrb orb : player.orbs) {
                if ("Frost".equals(orb.ID)) {
                    frostNum++;
                }
            }
        }

        if (orbNum >= 4) {
            CAN_NOT_APPEAR.remove("Fission");
            CAN_NOT_APPEAR.remove("Barrage");
        } else {
            if (!CAN_NOT_APPEAR.contains("Fission")) {
                CAN_NOT_APPEAR.add("Fission");
            }
            if (!CAN_NOT_APPEAR.contains("Barrage")) {
                CAN_NOT_APPEAR.add("Barrage");
            }
        }

        if (aliveMonster > 1) {
            CAN_NOT_APPEAR.remove("Chill");
        } else {
            if (!CAN_NOT_APPEAR.contains("Chill")) {
                CAN_NOT_APPEAR.add("Chill");
            }
        }

        if (player.energy.energyMaster < 4) {
            if (!CAN_NOT_APPEAR.contains("Meteor Strike")) {
                //删除陨石打击
                CAN_NOT_APPEAR.add("Meteor Strike");
            }
        }

        AbstractPower focus = player.getPower("Focus");
        int focusAmount = 0;
        if (focus != null){
            focusAmount = focus.amount;
        }
        if (player.currentBlock != 0) {
            if (!CAN_NOT_APPEAR.contains("Auto Shields")) {
                CAN_NOT_APPEAR.add("Auto Shields");
            }
            if (player.currentBlock >= 30 || (frostNum * focusAmount) >= 30) {
                System.out.println("已有30格挡,防御比重较大下降");//sout
                increase(map, "defend", -2);
            } else if (player.currentBlock >= 15 || (frostNum * focusAmount) >= 15) {
                System.out.println("已有15格挡,防御比重下降");//sout
                increase(map, "defend", -1);
            }
        }


        if (player.getPower("Mantra") == null) {
            CAN_NOT_APPEAR.add("Prostrate");
            CAN_NOT_APPEAR.add("Pray");
            CAN_NOT_APPEAR.add("Worship");
        }

        int strike = 0;
        for (AbstractCard card : player.masterDeck.group) {
            if (card.hasTag(AbstractCard.CardTags.STRIKE)) {
                strike++;
            }
        }
        if (strike < 6) {
            CAN_NOT_APPEAR.add("Perfected Strike");
        }
        return map;
    }

    //根据比分给卡的方法
    public void giveCard(Map<String,Integer> map){
        boolean needSecondWind = true;
        for (int i = 0; i < amount; i++) {
            String howToDo = chooseCardType(map);
            if (canNotUseCard >= 3 && needSecondWind) {
                AbstractCard card = new SecondWind();
                addOnlyThisTurnCardToHand(card);
                needSecondWind = false;
            } else if ("attack".equals(howToDo)) {
                addRandomCardToHand(AbstractCard.CardType.ATTACK, map);
                increase(map, "attack", -1);
            } else if ("defend".equals(howToDo)) {
                addRandomCardToHand(AbstractCard.CardType.SKILL, map);
                increase(map, "defend", -1);
            } else if ("buff".equals(howToDo)) {
                addRandomCardToHand(AbstractCard.CardType.POWER, map);
                increase(map, "buff", -1);
            }
        }
        canNotUseCard = 0;
    }


    /*@Override
    public void onCardDraw(AbstractCard theUseCard) {
        *//*if (canGiveCard) {
            drawCardCounter++;
            int drawDown = 0;
            if (AbstractDungeon.player.hasPower("Draw Down")){
                drawDown = AbstractDungeon.player.getPower("Draw Down").amount;
            }
            if (drawCardCounter == (AbstractDungeon.player.gameHandSize-drawDown) || AbstractDungeon.player.hand.group.size() >= 10) {

            }
        }*//*
    }*/

    private void increase(Map<String,Integer> map,String name){
        increase(map,name,1);
    }


    private void increase(Map<String,Integer> map,String name,int increase){
        int num;
        num = map.get(name);
        map.put(name,(num+increase));
        System.out.println(name+"+"+increase);
    }



    //获取卡,需要传入票数最高的的行动方案 和 各个行动方案:(攻击(attack),方式(defend),增益(buff)) 获得的票数,以map的形式传入
    private void addRandomCardToHand(AbstractCard.CardType type,Map<String,Integer> map){

        AbstractCard card = /*new Piano();*/ AbstractDungeon.returnTrulyRandomCardInCombat(type).makeCopy();
        for (String cardId : CAN_NOT_APPEAR){
            if (cardId.equals(card.cardID)){
                addRandomCardToHand(type,map);
                return;
            }
        }
        if (type == AbstractCard.CardType.SKILL){
            for (String cardId : IS_SKILL_BUT_ATTACK){
                if (cardId.equals(card.cardID)){
                    if (!(map.get("attack")+1== map.get("defend"))){
                        addRandomCardToHand(type,map);
                        return;
                    }
                }
            }
        }
        /*if (type == AbstractCard.CardType.POWER){
            card = new DepravedForm();
        }else if (type == AbstractCard.CardType.ATTACK){
            card = new Strike_king();
        }else if (type == AbstractCard.CardType.SKILL){
            card = new Defend_king();
        }*/
       addOnlyThisTurnCardToHand(card);
    }

    private void addOnlyThisTurnCardToHand(AbstractCard card){
        card.setCostForTurn(card.cost-1);
        if (!card.isEthereal){
            card.rawDescription += " NL 虚无 。";
            card.isEthereal = true;
        }
        if(!card.exhaust ){
            card.rawDescription += " NL 消耗 。";
            card.exhaust = true;
        }
        card.initializeDescription();
        addToBot(new AddCardToHandAction(card));
    }

    private String chooseCardType(Map<String,Integer> map){
        AtomicReference<String> intent = new AtomicReference<>("");
        AtomicInteger maxIntentNum = new AtomicInteger(0);
        map.forEach((name,count)->{
            System.out.println(name + "---->" + count);
            //防御最优先
            if (maxIntentNum.get() == count && "defend".equals(name)){
                intent.set(name);
                return;
            }
            //攻击其次
            if (maxIntentNum.get() == count && "attack".equals(name) && !"defend".equals(intent.get())){
                intent.set(name);
                return;
            }
            if (maxIntentNum.get() < count){
                maxIntentNum.set(count);
                intent.set(name);
            }
        });
        return intent.get();
    }

    @Override
    public void onVictory() {
        CAN_NOT_APPEAR.remove("Meteor Strike");
        CAN_NOT_APPEAR.remove("Auto Shields");
        CAN_NOT_APPEAR.remove("Prostrate");
        CAN_NOT_APPEAR.remove("Pray");
        CAN_NOT_APPEAR.remove("Worship");
        CAN_NOT_APPEAR.remove("Perfected Strike");
        CAN_NOT_APPEAR.remove("MeteorStrike");
        CAN_NOT_APPEAR.remove("Fission");
        super.onVictory();
    }
}