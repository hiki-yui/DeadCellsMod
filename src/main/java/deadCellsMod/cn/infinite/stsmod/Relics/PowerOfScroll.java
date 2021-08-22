package deadCellsMod.cn.infinite.stsmod.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.chests.BossChest;
import com.megacrit.cardcrawl.rooms.TreasureRoomBoss;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static com.megacrit.cardcrawl.events.AbstractEvent.logMetricCardRemoval;
import static com.megacrit.cardcrawl.events.AbstractEvent.logMetricCardUpgrade;

public class PowerOfScroll extends CustomRelic {

    public static final String BASE_ID = "deadCells:PowerOfScroll";
    private static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(BASE_ID);
    private static final String IMG = "img/relics/PowerOfScroll.png";
    private static final int HP_LOSE = 10;
    private static final int SCROLL_NUM = 3;
    private boolean canIn = false;
    private boolean first = false;
    private boolean second = false;
    private boolean third = false;
    boolean isGame = false;
    boolean selected = false;
    boolean isScreenUpOpened = false;

    static final int RED_UPGRADED_CARD_NUM = 1;
    static final int PURPLE_DELETE_CARD_NUM = 1;
    static final int GREEN_GIVE_CARD_NUM = 1;

    public PowerOfScroll(){
        super(BASE_ID, new Texture(IMG),RelicTier.BOSS,LandingSound.MAGICAL);
    }

    public PowerOfScroll(String id, Texture texture, RelicTier tier, LandingSound sfx){
        super(id,texture,tier,sfx);
    }

    @Override
    public String getUpdatedDescription() {
        return STRINGS.DESCRIPTIONS[0] + HP_LOSE + STRINGS.DESCRIPTIONS[1] + SCROLL_NUM + STRINGS.DESCRIPTIONS[2];
    }

    public void superUpdate(){
        super.update();
    }
    @Override
    public void update() {
        super.update();
        if (isGame &&  AbstractDungeon.player.relics.get(AbstractDungeon.player.relics.size()-1).isDone) {
            //获取遗物
            if (canIn && !AbstractDungeon.isScreenUp && (!first || !second || !third) /*&&*/
                  /*  AbstractDungeon.getCurrRoom() instanceof TreasureRoomBoss*/) {
                System.out.println("开始选");
                TreasureRoomBoss roomBoss;
                BossChest bossChest;
                roomBoss = (TreasureRoomBoss) AbstractDungeon.getCurrRoom();
                bossChest = (BossChest) roomBoss.chest;
                bossChest.relics.clear();
                bossChest.relics.add(new Alive().makeCopy());
                bossChest.relics.add(new Tactical().makeCopy());
                bossChest.relics.add(new Oppressive().makeCopy());
                AbstractDungeon.bossRelicScreen.open(bossChest.relics);
                if (!first) {
                    first = true;
                } else {
                    if (!second) {
                        second = true;
                    } else {
                        third = true;
                        canIn = false;
                    }
                }
            }

            //色卷轴的结算独立出来
            /*if (selectOver && !updateOver && !AbstractDungeon.isScreenUp) {
                if (greenNeedOpen) {
                    updateGreen();
                    greenNeedOpen = false;
                }
                updateOver = true;
            }*/
        }
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.decreaseMaxHealth(HP_LOSE);
        this.canIn = true;
        this.isGame = true;
        super.onEquip();
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PowerOfScroll();
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.getCurrRoom() instanceof TreasureRoomBoss;
    }

    public boolean openRedSelectScreen(int upgradeCardNum){
        System.out.println("要升级" +upgradeCardNum+ "张牌");
        ArrayList<AbstractCard> deck = AbstractDungeon.player.masterDeck.getUpgradableCards().group;
        if (deck.size() >= upgradeCardNum) {
            AbstractDungeon.gridSelectScreen.open(
                    AbstractDungeon.player.masterDeck.getUpgradableCards(),
                    upgradeCardNum, "升级一张卡", true, false, false, false);
        }else{
            AbstractDungeon.gridSelectScreen.open(
                    AbstractDungeon.player.masterDeck.getUpgradableCards(),
                    deck.size(), "升级一张卡", true, false, false, false);
        }
        return true;
    }

    public void openGreenSelectScreen(){
        updateGreen();
    }

    public boolean openPurpleSelectScreen(int cardNum){
        System.out.println("要删除" +cardNum+ "张牌");
        if (AbstractDungeon.player.masterDeck.group.size() >= cardNum) {
            AbstractDungeon.gridSelectScreen.open
                    (CardGroup.getGroupWithoutBottledCards(
                            AbstractDungeon.player.masterDeck.getPurgeableCards()),
                            cardNum, "战术", false, false, false, true);
        }else {
            AbstractDungeon.gridSelectScreen.open
                    (CardGroup.getGroupWithoutBottledCards(
                            AbstractDungeon.player.masterDeck.getPurgeableCards()),
                            AbstractDungeon.player.masterDeck.group.size(), "战术", false, false, false, true);
        }
        return true;
    }

    public void updateRed(){
        /*System.out.println("升级");*/
        if (AbstractDungeon.gridSelectScreen.selectedCards.size()>0){
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                c.upgrade();
                logMetricCardUpgrade("Accursed Blacksmith", "Forge", c);
                AbstractDungeon.player.bottledCardUpgradeCheck(c);
                AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy()));
                AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
    }

    public void updateGreen(){
        /*AbstractDungeon.combatRewardScreen.open("生存");*/
        /*AbstractDungeon.nextRoomTransitionStart();*/
        /*AbstractDungeon.player.increaseMaxHp();*/
    }

    public void updatePurple(){
        /*System.out.println("删除");*/
        if (AbstractDungeon.gridSelectScreen.selectedCards.size()>0){
            for (AbstractCard c:AbstractDungeon.gridSelectScreen.selectedCards) {
                CardCrawlGame.sound.play("CARD_EXHAUST");
                logMetricCardRemoval("Purifier", "Purged",c);
                AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(c, (Settings.WIDTH / 2), (Settings.HEIGHT / 2)));
                AbstractDungeon.player.masterDeck.removeCard(c);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
    }

    public void allUpdate(Method color){
        if (isGame && AbstractDungeon.player.relics.get(AbstractDungeon.player.relics.size()-1).isDone && selected){
            if (AbstractDungeon.gridSelectScreen.confirmScreenUp){
                isScreenUpOpened = true;
            }
            if (isScreenUpOpened &&
                    !AbstractDungeon.gridSelectScreen.confirmScreenUp &&
                    AbstractDungeon.gridSelectScreen.selectedCards.size()!=0){
                try {
                   color.invoke(this);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                selected = false;
            }
        }
    }
    public ScrollType scrollType= ScrollType.NONE;
    public static enum ScrollType{
        RED,
        PURPLE,
        GREEN,
        NONE
    }
}
