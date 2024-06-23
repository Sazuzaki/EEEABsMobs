package com.eeeab.eeeabsmobs.sever.entity.ai.goal;

import com.eeeab.animate.server.animation.Animation;
import com.eeeab.eeeabsmobs.sever.entity.effects.EntityCameraShake;
import com.eeeab.eeeabsmobs.sever.entity.effects.EntityGuardianBlade;
import com.eeeab.eeeabsmobs.sever.entity.guling.EntityNamelessGuardian;
import com.eeeab.eeeabsmobs.sever.entity.util.ModEntityUtils;
import com.eeeab.eeeabsmobs.sever.init.EffectInit;
import com.eeeab.eeeabsmobs.sever.init.SoundInit;
import com.eeeab.eeeabsmobs.sever.util.damge.EMDamageSource;
import com.eeeab.animate.server.ai.AnimationAI;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class GuardianShakeGroundAttackGoal extends AnimationAI<EntityNamelessGuardian> {

    public GuardianShakeGroundAttackGoal(EntityNamelessGuardian entity) {
        super(entity);
    }

    @Override
    protected boolean test(Animation animation) {
        return animation == this.entity.shakeGroundAttackAnimation1 || animation == this.entity.shakeGroundAttackAnimation2 || animation == this.entity.shakeGroundAttackAnimation3;
    }

    @Override
    public void tick() {
        int tick = this.entity.getAnimationTick();
        LivingEntity target = this.entity.getTarget();
        Animation animation = this.entity.getAnimation();
        entity.setDeltaMovement(0, entity.onGround() ? 0 : entity.getDeltaMovement().y, 0);
        if (animation == this.entity.shakeGroundAttackAnimation1) {
            if (tick > 10 && tick < 25 && target != null) {
                this.entity.lookAt(target, 30F, 30F);
            } else {
                this.entity.setYRot(this.entity.yRotO);
            }
            if (tick == 19) {
                this.entity.playSound(SoundInit.NAMELESS_GUARDIAN_WHOOSH.get(), 1.95f, this.entity.getVoicePitch());
                if (this.entity.targetDistance > 1.8 || entity.getTarget() == null)
                    this.entity.move(MoverType.SELF, new Vec3(Math.cos(Math.toRadians(entity.getYRot() + 90)) * 1.2F, 0, Math.sin(Math.toRadians(entity.getYRot() + 90)) * 1.2F));
            } else if (tick == 24) {
                final float attackArc = 40F;
                final float range = 4.6F;
                List<LivingEntity> entities = entity.getNearByLivingEntities(range, range - 0.6F, range, range);
                for (LivingEntity hitEntity : entities) {
                    float entityRelativeAngle = ModEntityUtils.getTargetRelativeAngle(entity, hitEntity);
                    float entityHitDistance = (float) Math.sqrt((hitEntity.getZ() - entity.getZ()) * (hitEntity.getZ() - entity.getZ()) + (hitEntity.getX() - entity.getX()) * (hitEntity.getX() - entity.getX())) - hitEntity.getBbWidth() / 2F;
                    if ((entityHitDistance <= range && (entityRelativeAngle <= attackArc / 2F && entityRelativeAngle >= -attackArc / 2F) || (entityRelativeAngle >= 360 - attackArc / 2F || entityRelativeAngle <= -360 + attackArc / 2F))) {
                        entity.guardianHurtTarget(EMDamageSource.guardianRobustAttack(entity), entity, hitEntity, 0.025F, 1F, 1.2F, true, true, true);
                    }
                }
                doSpawnBlade(2, 0.4F);
            } else if (tick == 25) {
                this.entity.playSound(SoundInit.GIANT_AXE_HIT.get(), 1.5F, 0.2F);
                EntityCameraShake.cameraShake(entity.level(), entity.position(), 20, 0.125F, 5, 10);
            } else if (tick == 35) {
                this.entity.playAnimation(this.entity.shakeGroundAttackAnimation2);
            }
        } else if (animation == this.entity.shakeGroundAttackAnimation2) {
            tick = this.entity.getAnimationTick();
            if (tick > 8 && tick < 18 && target != null) {
                this.entity.lookAt(target, 30F, 30F);
            } else {
                this.entity.setYRot(this.entity.yRotO);
            }
            if (tick == 13) {
                this.entity.playSound(SoundInit.NAMELESS_GUARDIAN_WHOOSH.get(), 1.95f, this.entity.getVoicePitch());
                if (this.entity.targetDistance > 1.8 || entity.getTarget() == null)
                    this.entity.move(MoverType.SELF, new Vec3(Math.cos(Math.toRadians(entity.getYRot() + 90)) * 1.2F, 0, Math.sin(Math.toRadians(entity.getYRot() + 90)) * 1.2F));
            } else if (tick == 17) {
                final float attackArc = 40F;
                final float range = 4.6F;
                List<LivingEntity> entities = entity.getNearByLivingEntities(range, range - 0.6F, range, range);
                for (LivingEntity hitEntity : entities) {
                    float entityRelativeAngle = ModEntityUtils.getTargetRelativeAngle(entity, hitEntity);
                    float entityHitDistance = (float) Math.sqrt((hitEntity.getZ() - entity.getZ()) * (hitEntity.getZ() - entity.getZ()) + (hitEntity.getX() - entity.getX()) * (hitEntity.getX() - entity.getX())) - hitEntity.getBbWidth() / 2F;
                    if ((entityHitDistance <= range && (entityRelativeAngle <= attackArc / 2F && entityRelativeAngle >= -attackArc / 2F) || (entityRelativeAngle >= 360 - attackArc / 2F || entityRelativeAngle <= -360 + attackArc / 2F))) {
                        entity.guardianHurtTarget(EMDamageSource.guardianRobustAttack(entity), entity, hitEntity, 0.025F, 1F, 1.0F, true, true, true);
                    }
                }
                doSpawnBlade(2, 0.4F);
            } else if (tick == 18) {
                this.entity.playSound(SoundInit.GIANT_AXE_HIT.get(), 1.5F, 0.2F);
                EntityCameraShake.cameraShake(entity.level(), entity.position(), 20, 0.125F, 5, 10);
            } else if (tick == 30 && this.entity.isPowered()) {
                this.entity.playAnimation(this.entity.shakeGroundAttackAnimation3);
            }
        } else if (animation == this.entity.shakeGroundAttackAnimation3) {
            tick = this.entity.getAnimationTick();
            if (tick > 8 && tick < 25 && target != null) {
                this.entity.lookAt(target, 30F, 30F);
            } else {
                this.entity.setYRot(this.entity.yRotO);
            }
            if (tick == 22) {
                this.entity.playSound(SoundInit.NAMELESS_GUARDIAN_WHOOSH.get(), 1.95f, this.entity.getVoicePitch());
                if (this.entity.targetDistance > 1.8 || entity.getTarget() == null)
                    this.entity.move(MoverType.SELF, new Vec3(Math.cos(Math.toRadians(entity.getYRot() + 90)) * 1.2F, 0, Math.sin(Math.toRadians(entity.getYRot() + 90)) * 1.2F));
            } else if (tick == 26) {
                final float attackArc = 40F;
                final float range = 4.6F;
                List<LivingEntity> entities = entity.getNearByLivingEntities(range, range - 0.6F, range, range);
                for (LivingEntity hitEntity : entities) {
                    float entityRelativeAngle = ModEntityUtils.getTargetRelativeAngle(entity, hitEntity);
                    float entityHitDistance = (float) Math.sqrt((hitEntity.getZ() - entity.getZ()) * (hitEntity.getZ() - entity.getZ()) + (hitEntity.getX() - entity.getX()) * (hitEntity.getX() - entity.getX())) - hitEntity.getBbWidth() / 2F;
                    if ((entityHitDistance <= range && (entityRelativeAngle <= attackArc / 2F && entityRelativeAngle >= -attackArc / 2F) || (entityRelativeAngle >= 360 - attackArc / 2F || entityRelativeAngle <= -360 + attackArc / 2F))) {
                        double duration = 2;
                        if (Difficulty.HARD.equals(this.entity.level().getDifficulty())) duration = 4;
                        if (hitEntity instanceof Player player && !player.isCreative() && !player.isBlocking()) {
                            player.addEffect(new MobEffectInstance(EffectInit.VERTIGO_EFFECT.get(), (int) (duration * 20), 0, false, false, true));
                        } else if (!(hitEntity instanceof Player) && !hitEntity.isBlocking()) {
                            hitEntity.addEffect(new MobEffectInstance(EffectInit.VERTIGO_EFFECT.get(), (int) (duration * 20), 0, false, false, true));
                        }
                        entity.guardianHurtTarget(EMDamageSource.guardianRobustAttack(entity), entity, hitEntity, 0.025F, 1.5F, 1.2F, true, true, true);
                    }
                }
                doSpawnBlade(3, 0.3F);
                this.entity.playSound(SoundInit.GIANT_AXE_HIT.get(), 1.5F, 0.2F);
                EntityCameraShake.cameraShake(entity.level(), entity.position(), 20, 0.2F, 5, 10);
            }
        }
    }

    private void doSpawnBlade(int count, float offset) {
        Vec3 looking = entity.getLookAngle();
        for (int i = -count; i < count; i++) {
            if (count % 2 == 0 && i == 0) {
                doSpawnBlade(looking);
                count++;
            } else {
                doSpawnBlade(looking.yRot(i * offset));
            }
        }
    }

    private void doSpawnBlade(Vec3 vec3) {
        float f0 = (float) Mth.atan2(vec3.z, vec3.x);
        double x = entity.getX() + Mth.cos(f0) * 2.5D;
        double y = entity.getY();
        double z = entity.getZ() + Mth.sin(f0) * 2.5D;
        EntityGuardianBlade blade = new EntityGuardianBlade(entity.level(), entity, x, y, z, f0, false);
        entity.level().addFreshEntity(blade);
    }

}
