package com.grizzly.client;

import com.grizzly.GrizzlyMod;
import com.grizzly.entity.GrizzlyBear;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/**
 * Modelo "ositofin" (hecho en Blockbench). Textura 128x128 con distribución propia:
 * cabeza (0,0), hocico (46,0), orejas (68,0), collar (80,0), pecho (0,22),
 * parte trasera (0,53), tira (0,88), placas (64,22), vientre (0,96), cola (90,22),
 * patas (56,96), pelaje de patas (90,96) y garras (90,30).
 */
public class GrizzlyBearModel extends EntityModel<GrizzlyBear> {

    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(GrizzlyMod.MODID, "grizzly_bear"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart rightEar;
    private final ModelPart leftEar;
    private final ModelPart frontRightLeg;
    private final ModelPart frontLeftLeg;
    private final ModelPart backRightLeg;
    private final ModelPart backLeftLeg;

    public GrizzlyBearModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.tail = this.body.getChild("tail");
        this.rightEar = this.head.getChild("right_ear");
        this.leftEar = this.head.getChild("left_ear");
        this.frontRightLeg = root.getChild("front_right_leg");
        this.frontLeftLeg = root.getChild("front_left_leg");
        this.backRightLeg = root.getChild("back_right_leg");
        this.backLeftLeg = root.getChild("back_left_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // Cabeza + hocico
        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-6.0F, -5.0F, -8.0F, 12.0F, 11.0F, 9.0F)
                        .texOffs(46, 0).addBox(-3.0F, 0.0F, -11.0F, 6.0F, 5.0F, 3.0F),
                PartPose.offset(0.0F, 0.0F, -4.0F));

        // Orejas
        head.addOrReplaceChild("right_ear", CubeListBuilder.create()
                        .texOffs(68, 0).addBox(-1.5F, -3.0F, -1.0F, 3.0F, 3.0F, 2.0F),
                PartPose.offset(4.5F, -5.0F, -3.0F));
        head.addOrReplaceChild("left_ear", CubeListBuilder.create()
                        .texOffs(68, 0).addBox(-1.5F, -3.0F, -1.0F, 3.0F, 3.0F, 2.0F),
                PartPose.offset(-4.5F, -5.0F, -3.0F));

        // Collar de pelo
        root.addOrReplaceChild("ruff", CubeListBuilder.create()
                        .texOffs(80, 0).addBox(-9.0F, -7.0F, -7.0F, 18.0F, 14.0F, 3.0F),
                PartPose.offset(0.0F, 2.0F, 0.0F));

        // Cuerpo: pecho, parte trasera, tira del lomo, placas laterales y vientre
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(0, 22).addBox(-10.0F, -11.0F, -15.0F, 20.0F, 19.0F, 11.0F)
                        .texOffs(0, 53).addBox(-10.0F, -6.0F, -4.0F, 20.0F, 14.0F, 20.0F)
                        .texOffs(0, 88).addBox(-9.0F, -9.0F, -4.0F, 18.0F, 3.0F, 4.0F)
                        .texOffs(64, 22).addBox(-11.5F, -11.0F, -14.5F, 2.0F, 17.0F, 10.0F)
                        .texOffs(64, 22).addBox(9.5F, -11.0F, -14.5F, 2.0F, 17.0F, 10.0F)
                        .texOffs(0, 96).addBox(-5.0F, 9.0F, -8.0F, 10.0F, 2.0F, 16.0F),
                PartPose.offset(0.0F, 2.0F, 12.0F));

        // Cola
        body.addOrReplaceChild("tail", CubeListBuilder.create()
                        .texOffs(90, 22).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 2.0F),
                PartPose.offset(0.0F, -0.5F, 16.0F));

        // Patas: pata + pelaje arriba + tres garras
        root.addOrReplaceChild("front_right_leg", CubeListBuilder.create()
                        .texOffs(56, 96).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 13.0F, 8.0F)
                        .texOffs(90, 96).addBox(-4.5F, -1.0F, -4.5F, 9.0F, 5.0F, 9.0F)
                        .texOffs(90, 30).addBox(-3.5F, 12.0F, -5.5F, 1.0F, 1.0F, 2.0F)
                        .texOffs(90, 30).addBox(-0.5F, 12.0F, -5.5F, 1.0F, 1.0F, 2.0F)
                        .texOffs(90, 30).addBox(2.5F, 12.0F, -5.5F, 1.0F, 1.0F, 2.0F),
                PartPose.offset(6.5F, 11.0F, 1.5F));
        root.addOrReplaceChild("front_left_leg", CubeListBuilder.create()
                        .texOffs(56, 96).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 13.0F, 8.0F)
                        .texOffs(90, 96).addBox(-4.5F, -1.0F, -4.5F, 9.0F, 5.0F, 9.0F)
                        .texOffs(90, 30).addBox(-3.5F, 12.0F, -5.5F, 1.0F, 1.0F, 2.0F)
                        .texOffs(90, 30).addBox(-0.5F, 12.0F, -5.5F, 1.0F, 1.0F, 2.0F)
                        .texOffs(90, 30).addBox(2.5F, 12.0F, -5.5F, 1.0F, 1.0F, 2.0F),
                PartPose.offset(-6.5F, 11.0F, 1.5F));
        root.addOrReplaceChild("back_right_leg", CubeListBuilder.create()
                        .texOffs(56, 96).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 13.0F, 8.0F)
                        .texOffs(90, 96).addBox(-4.5F, -1.0F, -4.5F, 9.0F, 5.0F, 9.0F)
                        .texOffs(90, 30).addBox(-3.5F, 12.0F, -6.0F, 1.0F, 1.0F, 2.0F)
                        .texOffs(90, 30).addBox(-0.5F, 12.0F, -6.0F, 1.0F, 1.0F, 2.0F)
                        .texOffs(90, 30).addBox(2.5F, 12.0F, -6.0F, 1.0F, 1.0F, 2.0F),
                PartPose.offset(6.5F, 11.0F, 22.0F));
        root.addOrReplaceChild("back_left_leg", CubeListBuilder.create()
                        .texOffs(56, 96).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 13.0F, 8.0F)
                        .texOffs(90, 96).addBox(-4.5F, -1.0F, -4.5F, 9.0F, 5.0F, 9.0F)
                        .texOffs(90, 30).addBox(-3.5F, 12.0F, -6.0F, 1.0F, 1.0F, 2.0F)
                        .texOffs(90, 30).addBox(-0.5F, 12.0F, -6.0F, 1.0F, 1.0F, 2.0F)
                        .texOffs(90, 30).addBox(2.5F, 12.0F, -6.0F, 1.0F, 1.0F, 2.0F),
                PartPose.offset(-6.5F, 11.0F, 22.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(GrizzlyBear bear, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        this.head.xRot = headPitch * Mth.DEG_TO_RAD;

        // Cachorro: cabeza proporcionalmente más grande
        float headScale = bear.isBaby() ? 1.3F : 1.0F;
        this.head.xScale = headScale;
        this.head.yScale = headScale;
        this.head.zScale = headScale;

        float t = limbSwing * 0.6662F;
        float swing = 1.0F * limbSwingAmount;
        this.backRightLeg.xRot = Mth.cos(t) * swing;
        this.frontLeftLeg.xRot = Mth.cos(t) * swing;
        this.backLeftLeg.xRot = Mth.cos(t + Mth.PI) * swing;
        this.frontRightLeg.xRot = Mth.cos(t + Mth.PI) * swing;

        // Caminar pachoncito: el cuerpo se mece de un lado a otro (como un pato grande)
        this.body.zRot = Mth.sin(t) * 0.15F * limbSwingAmount;
        this.body.yRot = Mth.cos(t) * 0.07F * limbSwingAmount;

        // Movimientos en reposo. Cada oso tiene su propio desfase (según su id)
        // para que no muevan las orejas todos a la vez.
        float idle = ageInTicks + bear.getId() * 53.0F;

        // Orejas: de izquierda a derecha, suave, una vez cada 20 s (400 ticks); dura 1.8 s
        float earT = (idle % 400.0F) / 36.0F;
        float earSway = earT < 1.0F
                ? Mth.sin(earT * Mth.PI * 4.0F) * Mth.sin(earT * Mth.PI)
                : 0.0F;
        this.rightEar.zRot = earSway * 0.35F;
        this.leftEar.zRot = earSway * 0.35F;

        // Cola: de izquierda a derecha una vez cada 15 s (300 ticks); dura 2.25 s.
        // Se suma al vaivén normal de la cola al caminar.
        float tailT = ((idle + 137.0F) % 300.0F) / 45.0F;
        float tailWag = tailT < 1.0F
                ? Mth.sin(tailT * Mth.PI * 6.0F) * Mth.sin(tailT * Mth.PI)
                : 0.0F;
        this.tail.yRot = Mth.cos(t) * 0.25F * limbSwingAmount + tailWag * 0.6F;

        // Animación al recibir miel: levanta el hocico, "mastica" y alza las patas delanteras
        int eat = bear.getEatTicks();
        if (eat > 0) {
            float e = Math.min(1.0F, eat / 8.0F);
            float chew = Mth.sin(ageInTicks * 0.9F);
            this.head.xRot = Mth.lerp(e, this.head.xRot, -0.55F + chew * 0.18F);
            this.head.yRot = Mth.lerp(e, this.head.yRot, 0.0F);
            this.frontRightLeg.xRot = Mth.lerp(e, this.frontRightLeg.xRot, -0.8F + chew * 0.12F);
            this.frontLeftLeg.xRot = Mth.lerp(e, this.frontLeftLeg.xRot, -0.8F - chew * 0.12F);
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer,
                               int packedLight, int packedOverlay, int color) {
        this.root.render(poseStack, buffer, packedLight, packedOverlay, color);
    }
}
