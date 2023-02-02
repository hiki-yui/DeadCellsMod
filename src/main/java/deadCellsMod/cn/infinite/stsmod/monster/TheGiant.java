package deadCellsMod.cn.infinite.stsmod.monster;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.MonsterStrings;

public class TheGiant extends DeadCellsMonster{

    public static final String BASE_ID = "deadCells:TheGiant";
    public static final MonsterStrings STRINGS = CardCrawlGame.languagePack.getMonsterStrings(BASE_ID);

    public TheGiant(){
        super(STRINGS.NAME,BASE_ID,250,-10.0F, -30.0F, 476.0F, 410.0F, null, -50.0F, 30.0F);
        loadAnimation("img/monster/TheGiant.atlas","img/monster/TheGiant.json",1.0f);
        AnimationState.TrackEntry e = this.state.setAnimation(0,"Idle1",true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.stateData.setMix("Idle2","Idle1",0.1f);
        e.setTimeScale(0.8f);

        this.type = EnemyType.BOSS;

    }


    @Override
    public void takeTurn() {
        addToBot(new RollMoveAction(this));
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
    }

    @Override
    protected void renderDamageRange(SpriteBatch sb) {
        super.renderDamageRange(sb);
    }

    @Override
    protected void renderIntentVfxBehind(SpriteBatch sb) {
        super.renderIntentVfxBehind(sb);
    }

    @Override
    protected void renderIntent(SpriteBatch sb) {
        super.renderIntent(sb);
    }

    @Override
    protected void getMove(int i) {

    }

    @Override
    protected void renderIntentVfxAfter(SpriteBatch sb) {
        super.renderIntentVfxAfter(sb);
    }

    @Override
    protected void renderName(SpriteBatch sb) {
        super.renderName(sb);
    }
}
