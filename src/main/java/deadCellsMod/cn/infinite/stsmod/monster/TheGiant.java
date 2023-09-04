package deadCellsMod.cn.infinite.stsmod.monster;

import basemod.abstracts.CustomMonster;
import basemod.helpers.ScreenPostProcessorManager;
import basemod.interfaces.ScreenPostProcessor;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import deadCellsMod.cn.infinite.stsmod.action.ChangeStateUseAction;
import deadCellsMod.cn.infinite.stsmod.action.LatterAction;
import deadCellsMod.cn.infinite.stsmod.effects.FocusProcessor;
import deadCellsMod.cn.infinite.stsmod.effects.GiantHowlEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TheGiant extends CustomMonster {

    public static final String BASE_ID = "deadCells:TheGiant";
    public static final MonsterStrings STRINGS = CardCrawlGame.languagePack.getMonsterStrings(BASE_ID);
    public static final Logger logger = LogManager.getLogger(TheGiant.class.getName());
    private boolean firstTurn;
    private ScreenPostProcessor screenPostProcessor = new FocusProcessor(this);

    public TheGiant(){
        super(STRINGS.NAME,BASE_ID, 700, 20.0F, -20.0F, 700.0F, 400.0F, null, -20.0F, 20.0F);
        this.firstTurn = true;
        loadAnimation("img/monster/TheGiant.atlas","img/monster/TheGiant.json",1.0f);
        AnimationState.TrackEntry e = this.state.setAnimation(0,"none",true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.stateData.setMix("Idle1", "Idle2", 0.2f);
        e.setTimeScale(0.5f);
        this.type = EnemyType.BOSS;
    }

    public void usePreBattleAction() {
        super.usePreBattleAction();
        (AbstractDungeon.getCurrRoom()).cannotLose = true;
        this.halfDead = true;
        this.setHp(0);
        updateHealthBar();
    }

    @Override
    public void takeTurn() {
        switch (this.nextMove){
            case 0:
                AbstractDungeon.actionManager.addToBottom(new HideHealthBarAction((AbstractCreature)this));
                AbstractDungeon.actionManager.addToBottom(new LatterAction( ()->{
                    ScreenPostProcessorManager.addPostProcessor(this.screenPostProcessor);
                }, 1.2F));

                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, STRINGS.DIALOG[8], 2.0F, 2.0F));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateUseAction((AbstractCreature)this, "fuck_die1", false, 1.0F, 3.5F));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, STRINGS.DIALOG[9], 2.0F, 2.0F));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, STRINGS.DIALOG[10], 2.0F, 2.0F));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, STRINGS.DIALOG[11], 2.0F, 2.0F));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateUseAction((AbstractCreature)this, "fuck_die2", false, 1.0F, 2.5F));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, STRINGS.DIALOG[12], 2.0F, 2.0F));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, STRINGS.DIALOG[13], 2.0F, 2.0F));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateUseAction((AbstractCreature)this, "fuck_die3", false, 1.0F, 1.5F));

                AbstractDungeon.actionManager.addToBottom(new LatterAction( ()->{
                    ScreenPostProcessorManager.removePostProcessor(this.screenPostProcessor);
                }, 1.2F));
                break;
            case 1:
                AbstractDungeon.actionManager.addToBottom(new HideHealthBarAction((AbstractCreature)this));
                /*
                AbstractDungeon.actionManager.addToBottom(new LatterAction( ()->{
                    ScreenPostProcessorManager.addPostProcessor(this.screenPostProcessor);
                }, 1.2F));

                 */

                AbstractDungeon.actionManager.addToBottom(new ChangeStateUseAction(this, "rua3", false, 1.0F, 2.0F));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new GiantHowlEffect(this), 1.5F));
                //FIRST_SEE ACTIONS
                /*
                AbstractDungeon.actionManager.addToBottom(new ChangeStateUseAction(this, "first_see", false, 0.8F, 2.0F));
                AbstractDungeon.actionManager.addToBottom(new TalkAction(this, STRINGS.DIALOG[0], 2.0F, 2.0F));
                AbstractDungeon.actionManager.addToBottom(new LatterAction(null, 1.0F));
                AbstractDungeon.actionManager.addToBottom(new TalkAction(this, STRINGS.DIALOG[1], 2.0F, 2.0F));
                AbstractDungeon.actionManager.addToBottom(new LatterAction(null, 4.0F));
                AbstractDungeon.actionManager.addToBottom(new TalkAction(this, STRINGS.DIALOG[2], 1.0F, 1.0F));
                AbstractDungeon.actionManager.addToBottom(new LatterAction(null, 1.0F));
                AbstractDungeon.actionManager.addToBottom(new TalkAction(this, STRINGS.DIALOG[3], 1.5F, 1.5F));
                AbstractDungeon.actionManager.addToBottom(new LatterAction(null, 0.5F));
                AbstractDungeon.actionManager.addToBottom(new TalkAction(this, STRINGS.DIALOG[4], 1.5F, 1.5F));
                AbstractDungeon.actionManager.addToBottom(new LatterAction(null, 0.5F));
                AbstractDungeon.actionManager.addToBottom(new TalkAction(this, STRINGS.DIALOG[5], 1.5F, 1.5F));
                AbstractDungeon.actionManager.addToBottom(new LatterAction(null, 0.5F));
                AbstractDungeon.actionManager.addToBottom(new TalkAction(this, STRINGS.DIALOG[6], 1.5F, 1.5F));
                AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                }, 0.5F));
                AbstractDungeon.actionManager.addToBottom(new TalkAction(this, STRINGS.DIALOG[7], 1.0F, 1.0F));

                AbstractDungeon.actionManager.addToBottom(new LatterAction( ()->{
                    ScreenPostProcessorManager.removePostProcessor(this.screenPostProcessor);
                }, 1.2F));

 */
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new HideHealthBarAction((AbstractCreature)this));
                AbstractDungeon.actionManager.addToBottom(new LatterAction( ()->{
                    ScreenPostProcessorManager.addPostProcessor(this.screenPostProcessor);
                }, 1.2F));

                AbstractDungeon.actionManager.addToBottom(new ChangeStateUseAction(this, "rua1", false, 1.0F, 2.0F));

                AbstractDungeon.actionManager.addToBottom(new LatterAction( ()->{
                    ScreenPostProcessorManager.removePostProcessor(this.screenPostProcessor);
                }, 1.2F));
                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom(new HideHealthBarAction((AbstractCreature)this));
                AbstractDungeon.actionManager.addToBottom(new LatterAction( ()->{
                    ScreenPostProcessorManager.addPostProcessor(this.screenPostProcessor);
                }, 1.2F));

                AbstractDungeon.actionManager.addToBottom(new ChangeStateUseAction(this, "rua2", false, 1.0F, 2.0F));

                AbstractDungeon.actionManager.addToBottom(new LatterAction( ()->{
                    ScreenPostProcessorManager.removePostProcessor(this.screenPostProcessor);
                }, 1.2F));
                break;
        }
        addToBot(new RollMoveAction(this));
    }

    @Override
    protected void getMove(int i) {
        this.setMove((byte) 1, Intent.UNKNOWN);
    }

    @Override
    public void update() {
        if(firstTurn){
            hideHealthBar();
        }
        super.update();
    }

    /*
    public class MyBoss extends AbstractMonster {
        private static final String BOSS_ATLAS = "images/monsters/boss/boss.atlas";
        private static final String BOSS_JSON = "images/monsters/boss/boss.json";
        private static final String SPAWN_ANIMATION = "spawn";
        private static final float SPAWN_ANIMATION_DELAY = 0.5f;
        private boolean spawnCompleted = false;
        private TextureRegion leftClawTexture, rightClawTexture;
        private float leftClawX, leftClawY, rightClawX, rightClawY;
        private float leftClawScaleX, leftClawScaleY, rightClawScaleX, rightClawScaleY;
        private float leftClawRotation, rightClawRotation;
        private float leftClawAlpha, rightClawAlpha;
        private boolean leftClawVisible, rightClawVisible;
        private float leftClawDepth, rightClawDepth;
        public MyBoss(...) {
            super(...);
            loadAnimation(BOSS_ATLAS, BOSS_JSON, 1.0f);
            setClaws(null, null, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, true, true, 0.0f, 0.0f);
            AnimationState.TrackEntry spawnAnimation = state.setAnimation(0, SPAWN_ANIMATION, false);
            spawnAnimation.setTime(spawnAnimation.getEndTime() * MathUtils.random());
            state.addAnimation(0, "idle", true, 0.0f);
        }
        public void setClaws(TextureRegion leftClawTexture, TextureRegion rightClawTexture, float leftClawX, float leftClawY,
                             float leftClawScaleX, float leftClawScaleY, float leftClawRotation, float leftClawAlpha,
                             float rightClawScaleX, float rightClawScaleY, float rightClawRotation, float rightClawAlpha,
                             boolean leftClawVisible, boolean rightClawVisible, float leftClawDepth, float rightClawDepth) {
            this.leftClawTexture = leftClawTexture;
            this.rightClawTexture = rightClawTexture;
            this.leftClawX = leftClawX;
            this.leftClawY = leftClawY;
            this.rightClawX = rightClawX;
            this.rightClawY = rightClawY;
            this.leftClawScaleX = leftClawScaleX;
            this.leftClawScaleY = leftClawScaleY;
            this.rightClawScaleX = rightClawScaleX;
            this.rightClawScaleY = rightClawScaleY;
            this.leftClawRotation = leftClawRotation;
            this.rightClawRotation = rightClawRotation;
            this.leftClawAlpha = leftClawAlpha;
            this.rightClawAlpha = rightClawAlpha;
            this.leftClawVisible = leftClawVisible;
            this.rightClawVisible = rightClawVisible;
            this.leftClawDepth = leftClawDepth;
            this.rightClawDepth = rightClawDepth;
            if (leftClawTexture != null) {
                state.getCurrent(0).getAttachment("left_claw").setAttachment(leftClawTexture);
                state.getCurrent(0).getAttachment("left_claw").getRenderable().setScale(leftClawScaleX, leftClawScaleY);
                state.getCurrent(0).getAttachment("left_claw").getRenderable().setRotation(leftClawRotation);
                state.getCurrent(0).getAttachment("left_claw").getRenderable().setAlpha(leftClawAlpha);
                state.getCurrent(0).getAttachment("left_claw").getRenderable().setVisible(leftClawVisible);
                state.getCurrent(0).getAttachment("left_claw").getRenderable().setDepth(leftClawDepth);
            }
            if (rightClawTexture != null) {
                state.getCurrent(0).getAttachment("right_claw").setAttachment(rightClawTexture);
                state.getCurrent(0).getAttachment("right_claw").getRenderable().setScale(rightClawScaleX, rightClawScaleY);
                state.getCurrent(0).getAttachment("right_claw").getRenderable().setRotation(rightClawRotation);
                state.getCurrent(0).getAttachment("right_claw").getRenderable().setAlpha(rightClawAlpha);
                state.getCurrent(0).getAttachment("right_claw").getRenderable().setVisible(rightClawVisible);
                state.getCurrent(0).getAttachment("right_claw").getRenderable().setDepth(rightClawDepth);
            }
        }
        @Override
        public void update() {
            super.update();
            if (spawnCompleted && leftClawTexture != null && rightClawTexture != null) {
                // create left claw monster
                // you can customize the left claw monster's stats, moves, animations, etc.
                AbstractMonster leftClaw = new AbstractMonster(leftClawX, leftClawY, leftClawName, leftClawID, leftClawMaxHP, leftClawHealthBarWidth, leftClawHealthBarOffset, null);
                leftClaw.loadAnimation(leftClawAtlas, leftClawJson, 1.0f);
                leftClaw.state.setAnimation(0, leftClawIdleAnimation, true);
                leftClaw.damage.add(new DamageInfo(leftClaw, leftClawDamage));
                // create right claw monster
                // you can customize the right claw monster's stats, moves, animations, etc.
                AbstractMonster rightClaw = new AbstractMonster(rightClawX, rightClawY, rightClawName, rightClawID, rightClawMaxHP, rightClawHealthBarWidth, rightClawHealthBarOffset, null);
                rightClaw.loadAnimation(rightClawAtlas, rightClawJson, 1.0f);
                rightClaw.state.setAnimation(0, rightClawIdleAnimation, true);
                rightClaw.damage.add(new DamageInfo(rightClaw, rightClawDamage));
                // add left claw and right claw monsters to monster group
                AbstractDungeon.getCurrRoom().monsters.addMonster(leftClaw);
                AbstractDungeon.getCurrRoom().monsters.addMonster(rightClaw);
                // set spawnCompleted flag to false to prevent this code from running again
                spawnCompleted = false;
            }
        }
        @Override
        public void onAnimationStateEvent(com.esotericsoftware.spine.AnimationState.TrackEntry event) {
            super.onAnimationStateEvent(event);
            if (event.getAnimation().getName().equals(SPAWN_ANIMATION) && event.getEventType() == AnimationStateListener.EventType.END) {
                spawnCompleted = true;
            }
        }
    ...
    }
    */
}
