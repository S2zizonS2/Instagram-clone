import InputProps from "@/Type/InputProps";
import styled from "@emotion/styled";
import React from "react";
import Input from "./Input";


interface AnimationPlaceHolderInputProps {
    input: InputProps;
    placeholder: string;
}

/**
 * 입력시 placeholder가 왼쪽 상단으로 작게 표시되는 애니메이션을 가진 인풋 컴포넌트
 * @className - animation-placeholder-input
 * @returns 
 */
function AnimationPlaceholderInput(props: AnimationPlaceHolderInputProps) {
    const isTyping = props.input.value?.length !== 0;
    return (
        <Label
            className={`animation-placeholder-input`}
            isTyping={isTyping}
        >
            <span
                className={`${isTyping ? "typing" : "non"}`}
            >
                {props.placeholder}
            </span>
            <Input
                input={props.input}
            />
        </Label >
    );
}

const Label = React.memo(styled.label<{
    isTyping: boolean;
}>`
    display: flex;
    width: 100%;
    position: relative;
    align-items: ${({ isTyping }) => isTyping ? "flex-start" : "center"};
    span {
        position: absolute;
        font-size: 0.8rem
    }

    .typing {
        animation: ani 0.2s ease-in-out forwards;
    }

    .non {
        animation: ani2 0.2s ease-in-out forwards;
    }
    @keyframes ani {
        100% {
            top: 0;
            font-size: 0.6rem;
            margin-top: 0.3rem;
        }
    }

    @keyframes ani2 {
        100% {
            font-size: 0.8rem;
            margin-top: 0;
        }
    }
`);

export default AnimationPlaceholderInput;