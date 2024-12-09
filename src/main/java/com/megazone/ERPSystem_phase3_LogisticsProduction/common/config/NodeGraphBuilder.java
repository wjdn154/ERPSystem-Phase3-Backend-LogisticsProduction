package com.megazone.ERPSystem_phase3_LogisticsProduction.common.config;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;
import jakarta.persistence.metamodel.PluralAttribute;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class NodeGraphBuilder {

    private final EntityManager entityManager;

    // 단방향 의존성 그래프
    @Getter
    private final Map<String, Set<String>> dependencyGraph = new HashMap<>();

    // 역방향 의존성 그래프
    @Getter
    private final Map<String, Set<String>> reverseDependencyGraph = new HashMap<>();

    public NodeGraphBuilder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostConstruct
    public void buildGraph() {
        Metamodel metamodel = entityManager.getMetamodel();

        // 모든 JPA 엔티티 탐색
        for (EntityType<?> entity : metamodel.getEntities()) {
            String entityName = entity.getName();
            dependencyGraph.putIfAbsent(entityName, new HashSet<>());
            reverseDependencyGraph.putIfAbsent(entityName, new HashSet<>());

            // 엔티티의 모든 속성 탐색
            entity.getAttributes().forEach(attribute -> {
                if (attribute.isAssociation()) {
                    if (attribute.getPersistentAttributeType() == Attribute.PersistentAttributeType.ONE_TO_MANY) {
                        // 컬렉션의 제네릭 타입 추출
                        Class<?> targetEntity = ((PluralAttribute<?, ?, ?>) attribute).getElementType().getJavaType();
                        dependencyGraph.get(entityName).add(targetEntity.getSimpleName());

                        // 역방향 추가
                        reverseDependencyGraph.putIfAbsent(targetEntity.getSimpleName(), new HashSet<>());
                        reverseDependencyGraph.get(targetEntity.getSimpleName()).add(entityName);

                    } else {
                        // 일반 연관관계 처리
                        String targetEntityName = attribute.getJavaType().getSimpleName();
                        dependencyGraph.get(entityName).add(targetEntityName);

                        // 역방향 추가
                        reverseDependencyGraph.putIfAbsent(targetEntityName, new HashSet<>());
                        reverseDependencyGraph.get(targetEntityName).add(entityName);
                    }
                }
            });
        }

        // 디버깅: 그래프 출력
        System.out.println("== 단방향 그래프 ==");
        dependencyGraph.forEach((key, value) ->
                System.out.println(key + " -> " + value)
        );

        System.out.println("== 역방향 그래프 ==");
        reverseDependencyGraph.forEach((key, value) ->
                System.out.println(key + " <- " + value)
        );
    }

    public boolean isBidirectional(String sourceEntity, String targetEntity) {
        // Source -> Target
        boolean sourceToTarget = dependencyGraph.containsKey(sourceEntity) &&
                dependencyGraph.get(sourceEntity).contains(targetEntity);

        // Target -> Source
        boolean targetToSource = reverseDependencyGraph.containsKey(targetEntity) &&
                reverseDependencyGraph.get(targetEntity).contains(sourceEntity);

        // 양방향 참조인지 확인
        return sourceToTarget && targetToSource;
    }
}
