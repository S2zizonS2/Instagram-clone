import React, { useCallback } from "react";
import InputProps from "@/Type/InputProps";
import AnimationPlaceholderInput from "@/components/common/AnimationPlaceholderInput";
import { AiOutlineCheckCircle, AiOutlineCloseCircle } from "react-icons/ai";
import styled from "@emotion/styled";

interface ValidInputProps {
    input: InputProps;
    placeholder: string;
    valid: boolean;
}

function ValidInput(props: ValidInputProps) {
    const valid = useCallback(() => {
        if (props.input.value?.length === 0) return null;
        if (props.valid) {
            return <AiOutlineCheckCircle style={{ color: "var(--grey-300)" }} />;
        } else {
            return <AiOutlineCloseCircle style={{ color: "var(--red-400)" }} />;
        }
    }, [props.input.value?.length, props.valid]);
    return (
        <Div
            name={props.input.name}
        >
            <AnimationPlaceholderInput
                input={props.input}
                placeholder={props.placeholder}
            />
            {valid()}
        </Div>
    );
}

const Div = styled.div<{
    name?: string;
}>`
    display: inline-flex;
    position: relative;
    width: 100%;
    align-items: center;
    margin-bottom: 0.5rem;

    &[name="password"] {
        justify-content: center;
    }

    input {
        padding-right:  2rem;
    }

    input[name="password"] {
        padding-right: 8.5rem;
    }

    svg {
        position: absolute;
        right: 0;
        margin-right: 0.5rem;
        font-size: 1.5rem;
    }

    &[name="password"] > svg {
        right: unset;
        transform: translateX(150%);
    }
`;

export default ValidInput;