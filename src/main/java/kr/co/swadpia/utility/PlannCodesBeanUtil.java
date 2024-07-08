package kr.co.swadpia.utility;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Slf4j
public class PlannCodesBeanUtil {

    /**
     * 객체 카피 유틸 -> 아래 example 메소드 참고
     * @param source 복사원본
     * @param target 복사할 class
     * @return target
     */
    public static <T> T copyPropertiesReturnTargetClass(Object source,  T target){
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * 리스트 카피 유틸 -> 아래 example2 메소드 참고
     * @param sourceList 복사원본 List
     * @param targetClass 복사할 List<클래스> 정보
     * @return List<targetClass>
     */
    public static <T> List<T> copyPropertiesToTargetListClass(List<?> sourceList, Class<T> targetClass) {
        return sourceList.stream()
                .map(sourceObject -> {
                    try {
                        T targetObject = targetClass.getDeclaredConstructor().newInstance();
                        BeanUtils.copyProperties(sourceObject, targetObject);
                        return targetObject;
                    } catch (Exception e) {
                        //exception을.. 잡아야하나
                        throw new RuntimeException("copyPropertiesToTargetListClass error: " + targetClass.getName(), e);
                    }
                })
                .toList();
    }

    ///예제
    public void example(){
//        CartParamDTO paramDTO = new CartParamDTO();
//        paramDTO.setCartId(1l);
//        paramDTO.setOrderNo("11111111");

//        CartDTO copy = GiftPiaBeanUtil.copyPropertiesReturnTargetClass(paramDTO, new CartDTO());

//        log.info("copy =  {} " , copy);
    }

    public void example2(){
//        CartParamDTO paramDTO = new CartParamDTO();
//        paramDTO.setCartId(1L);
//        paramDTO.setOrderNo("11111111");
//
//        List<CartParamDTO> list = new ArrayList<>();
//        list.add(paramDTO);

//        List<CartDTO> copyList = GiftPiaBeanUtil.copyPropertiesToTargetListClass(list, CartDTO.class);

//        log.info("copyList =  {} " , copyList);
    }
}

