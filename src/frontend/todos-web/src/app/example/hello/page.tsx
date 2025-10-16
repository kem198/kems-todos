import { Hello } from "@/components/example/hello/hello";
import { Page } from "@/components/layout/page";
import { TypographyH1 } from "@/components/typography/typography-h1";

export default function HelloPage() {
  return (
    <Page>
      <TypographyH1>Hello, World!</TypographyH1>
      <div className="flex flex-col gap-2">
        <Hello />
        <Hello name="KeM198" />
      </div>
    </Page>
  );
}
