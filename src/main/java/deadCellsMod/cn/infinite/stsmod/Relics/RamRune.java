package deadCellsMod.cn.infinite.stsmod.Relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.LoseBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.ArrayList;

public class RamRune extends DeadCellsRelic{
    public static final String BASE_ID = "deadCells:RamRune";
    private static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(BASE_ID);
    private static final String IMG = "img/relics/RamRune.png";
    private int atkNum = 0;
    public static int RANDOM = -1;

    public RamRune(){
        super(BASE_ID,new Texture(IMG),RelicTier.RARE,LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return STRINGS.DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        this.atkNum = 0;
        if(RANDOM >= 50){
            for(AbstractMonster m: AbstractDungeon.getCurrRoom().monsters.monsters){
                addToBot(new ApplyPowerAction(m, m, new StrengthPower(m, 2)));
                m.increaseMaxHp(15, true);
            }
        }
    }

    @Override
    public void onVictory() {
        super.onVictory();
        if(RANDOM >= 50){
            AbstractDungeon.getCurrRoom().addGoldToRewards(RANDOM + 10);
        }
    }

    @Override
    public void onPlayerEndTurn() {
        super.onPlayerEndTurn();
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        super.onPlayCard(c, m);
        if(c.type == AbstractCard.CardType.ATTACK){
            this.atkNum ++;
            if(this.atkNum >= 2){
                this.beginPulse();
                ArrayList<AbstractMonster> monsters = new ArrayList<>();
                for(AbstractMonster monster: AbstractDungeon.getCurrRoom().monsters.monsters){
                    if(monster.currentBlock > 0 || monster.hasPower(MinionPower.POWER_ID)){
                        monsters.add(monster);
                    }
                }
                if(this.atkNum >= 2 && monsters.size() != 0){
                    int rng = AbstractDungeon.monsterRng.random(monsters.size()-1);
                    AbstractMonster mo = monsters.get(rng);
                    if(mo.hasPower(MinionPower.POWER_ID)){
                        mo.loseBlock(true);
                        addToBot(new DamageAction(mo, new DamageInfo(null, mo.maxHealth, DamageInfo.DamageType.THORNS)));
                    }else {
                        addToBot(new LoseBlockAction(mo, null, mo.currentBlock));
                    }
                    this.flash();
                    this.atkNum = 0;
                }
            }
        }
    }
}
