import React from "react";
import InputProps from "@/Type/InputProps";
import AnimationPlaceholderInput from "@/components/common/AnimationPlaceholderInput";

interface ValidInputProps {
    input: InputProps;
    placeholder: string;
    valid: boolean;
}

function ValidInput(props: ValidInputProps) {
    return (
        <AnimationPlaceholderInput
            input={props.input}
            placeholder={props.placeholder}
        />
    );
}

export default ValidInput;